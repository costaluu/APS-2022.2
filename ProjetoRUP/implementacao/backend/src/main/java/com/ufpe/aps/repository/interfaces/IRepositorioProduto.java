package com.ufpe.aps.repository.interfaces;

import com.ufpe.aps.entity.Carrinho;
import com.ufpe.aps.entity.Produto;

import java.util.List;

public interface IRepositorioProduto {
    public void addAoEstoque(Produto produto);

    public Produto pegarProduto(String idProduto, Integer quantidade);

    public void atualizaEstoquesProdutos(Carrinho carrinho);

    public List<Produto> pegarTodosProdutos();
}
