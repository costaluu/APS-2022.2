package com.ufpe.aps.produto;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.exception.IsNotOwnerOfProductException;
import com.ufpe.aps.factory.impl.FabricaRepositorioBDR;
import com.ufpe.aps.factory.impl.FabricaRepositoriosInMemory;
import com.ufpe.aps.repository.bdr.ProdutoDAO;
import org.springframework.core.env.Environment;

import java.util.List;

public class FachadaProduto {

    private final IRegistroProduto registroProduto;

    private final IServicoProduto servicoProduto;

    private final ProdutoDAO repository;

    public FachadaProduto(Environment env, ProdutoDAO repository) {
        String choice = env.getProperty("fabrica.repositorios.choice") != null ? env.getProperty("fabrica.repositorios.choice") : "INMEMORY";
        assert choice != null;
        IRepositorioProduto repo = choice.equalsIgnoreCase("bdr") ?
                new FabricaRepositorioBDR(repository).criarRepositorioProduto() : new FabricaRepositoriosInMemory().criarRepositorioProduto();
        this.registroProduto = new RegistroProduto(repo);
        this.servicoProduto = new ServicoProduto(registroProduto);
        this.repository = repository;
    }


    public Produto pegarProduto(String id, int quantidade) {
        return this.registroProduto.pegarProduto(id, quantidade);
    }

    public void atualizarEstoque(Carrinho carrinho) {
        this.registroProduto.atualizarEstoque(carrinho);
    }

    public void criarProduto(String login, Produto produto, int quantidade) {
        this.registroProduto.criarProduto(login, produto, quantidade);
    }

    public List<Produto> meusProdutos(String login) {
        return this.servicoProduto.meusProdutos(login);
    }

    public void publicarItem(Produto produto) {
        this.servicoProduto.publicarItem(produto);
    }

    public void excluirProduto(String login, String idProduto) throws IsNotOwnerOfProductException {
        this.servicoProduto.excluirProduto(login, idProduto);
    }

    public void avaliar(String login, String idProduto, Integer avaliacao) {
        this.servicoProduto.avaliar(login, idProduto, avaliacao);
    }

    public List<Produto> pegarTodosProdutos() {
        return this.servicoProduto.pegarTodosProdutos();
    }
}
