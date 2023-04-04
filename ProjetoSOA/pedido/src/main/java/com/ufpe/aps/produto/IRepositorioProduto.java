package com.ufpe.aps.produto;

import com.ufpe.aps.carrinho.Carrinho;

import java.util.List;

public interface IRepositorioProduto {
    public void addAoEstoque(Produto produto);

    public Produto pegarProduto(String idProduto, Integer quantidade);

    public void atualizaEstoquesProdutos(Carrinho carrinho);

    public List<Produto> pegarTodosProdutos();
}
