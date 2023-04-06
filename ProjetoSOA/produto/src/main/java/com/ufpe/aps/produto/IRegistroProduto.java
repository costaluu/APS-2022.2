package com.ufpe.aps.produto;

import com.ufpe.aps.carrinho.Carrinho;

import java.util.List;

public interface IRegistroProduto {

    Produto pegarProduto(String id, int quantidade);

    void atualizarEstoque(Carrinho carrinho);

    void criarProduto(String login, Produto produto, int quantidade);

    void deletarProduto(String idProduto);

    void atualizarAvaliacao(String idProduto, Float avaliacao);

    List<Produto> pegarTodosProdutos();

    List<Produto> pegarMeusProdutos(String login);
}
