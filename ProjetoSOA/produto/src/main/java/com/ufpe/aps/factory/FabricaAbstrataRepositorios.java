package com.ufpe.aps.factory;

import com.ufpe.aps.produto.IRepositorioProduto;

public interface FabricaAbstrataRepositorios {
    public IRepositorioProduto criarRepositorioProduto();
}
