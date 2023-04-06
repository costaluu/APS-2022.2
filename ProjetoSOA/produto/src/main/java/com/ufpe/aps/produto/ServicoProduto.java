package com.ufpe.aps.produto;

import com.ufpe.aps.exception.IsNotOwnerOfProductException;
import com.ufpe.aps.factory.impl.FabricaRepositorioBDR;
import com.ufpe.aps.factory.impl.FabricaRepositoriosInMemory;
import com.ufpe.aps.repository.bdr.ProdutoDAO;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServicoProduto implements IServicoProduto {

    private final IRegistroProduto registroProduto;

    public ServicoProduto(IRegistroProduto registroProduto) {
        this.registroProduto = registroProduto;

//        String choice = env.getProperty("fabrica.repositorios.choice") != null ? env.getProperty("fabrica.repositorios.choice") : "INMEMORY";
//        assert choice != null;
//        IRepositorioProduto repo = choice.equalsIgnoreCase("bdr") ?
//                new FabricaRepositorioBDR(repository).criarRepositorioProduto() : new FabricaRepositoriosInMemory().criarRepositorioProduto();
//        this.registroProduto = new RegistroProduto(repo);
    }

    @Override
    public List<Produto> meusProdutos(String login) {
        return registroProduto.pegarMeusProdutos(login);
    }

    @Override
    public List<Produto> pegarTodosProdutos() {
        return registroProduto.pegarTodosProdutos();
    }

    @Override
    public void publicarItem(Produto produto) {
        registroProduto.criarProduto(produto.getDono(), produto, produto.getTotalUnidades());
    }

    @Override
    public void excluirProduto(String login, String idProduto) throws IsNotOwnerOfProductException {
        Produto produto = registroProduto.pegarProduto(idProduto, 1);
        if(!produto.getDono().equals(login))
            throw new IsNotOwnerOfProductException();
        registroProduto.deletarProduto(idProduto);
    }

    @Override
    public void avaliar(String login, String idProduto, Integer avaliacao) {
        Produto produto = registroProduto.pegarProduto(idProduto, 1);

        float novaAvaliacao = (produto.getAvaliacao() * produto.getQtdAvaliacoes() + avaliacao) / (produto.getQtdAvaliacoes() + 1);

        registroProduto.atualizarAvaliacao(idProduto, novaAvaliacao);
    }
}
