package com.ufpe.aps.factory.impl;

import com.ufpe.aps.factory.FabricaAbstrataRepositorios;
import com.ufpe.aps.produto.IRepositorioProduto;
import com.ufpe.aps.repository.bdr.CadastroProdutoBDR;
import com.ufpe.aps.repository.bdr.ProdutoDAO;

//@Component
public class FabricaRepositorioBDR implements FabricaAbstrataRepositorios {

    private final ProdutoDAO repository;

    public FabricaRepositorioBDR(ProdutoDAO repository) {
        this.repository = repository;
    }

    @Override
    public IRepositorioProduto criarRepositorioProduto() {
        return new CadastroProdutoBDR(repository);
    }
}
