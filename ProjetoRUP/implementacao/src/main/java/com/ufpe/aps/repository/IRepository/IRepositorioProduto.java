package com.ufpe.aps.repository.IRepository;

import com.ufpe.aps.entity.Produto;

public interface IRepositorioProduto {
    public void addAoEstoque(Produto produto);

    public Produto pegarProduto(String idProduto);

    public void atualizaEstoquesProdutos(Produto produto);
}
