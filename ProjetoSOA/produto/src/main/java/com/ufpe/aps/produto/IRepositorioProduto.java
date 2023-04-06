package com.ufpe.aps.produto;

import com.ufpe.aps.carrinho.Carrinho;

import java.util.List;

public interface IRepositorioProduto {
    void addAoEstoque(Produto produto);

    Produto pegarProduto(String idProduto, Integer quantidade);

    Produto pegarProduto(String idProduto);

    void atualizaEstoquesProdutos(Carrinho carrinho);

    List<Produto> pegarTodosProdutos();

    List<Produto> pegarMeusProdutos(String login);

    void deletarProduto(String idProduto);

    void atualizarAvaliacao(String idProduto, Float avaliacao);
}
