package com.ufpe.aps.produto;

import com.ufpe.aps.carrinho.Carrinho;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegistroProduto implements IRegistroProduto {

    private final IRepositorioProduto repositorioProduto;

    public RegistroProduto(IRepositorioProduto repositorioProduto) {
        this.repositorioProduto = repositorioProduto;
    }


    @Override
    public Produto pegarProduto(String id, int quantidade) {
        return repositorioProduto.pegarProduto(id, quantidade);
    }

    @Override
    public void atualizarEstoque(Carrinho carrinho) {
        repositorioProduto.atualizaEstoquesProdutos(carrinho);
    }

    @Override
    public void criarProduto(String login, Produto produto, int quantidade) {
        repositorioProduto.addAoEstoque(produto);
    }

    @Override
    public void deletarProduto(String idProduto) {
        repositorioProduto.deletarProduto(idProduto);
    }

    @Override
    public void atualizarAvaliacao(String idProduto, String avaliacao) {
        repositorioProduto.atualizarAvaliacao(idProduto, avaliacao);
    }

    @Override
    public List<Produto> pegarTodosProdutos(String login) {
        return repositorioProduto.pegarTodosProdutos();
    }
}
