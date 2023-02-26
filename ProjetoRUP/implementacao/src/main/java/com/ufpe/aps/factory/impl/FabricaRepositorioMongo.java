package com.ufpe.aps.factory.impl;

import com.ufpe.aps.factory.FabricaAbstrataRepositorios;
import com.ufpe.aps.repository.interfaces.IRepositorioConta;
import com.ufpe.aps.repository.interfaces.IRepositorioPedido;
import com.ufpe.aps.repository.interfaces.IRepositorioProduto;

public class FabricaRepositorioMongo implements FabricaAbstrataRepositorios {
    @Override
    public IRepositorioProduto criarProdutoRepository() {
        return null;
    }

    @Override
    public IRepositorioPedido criarPedidoRepository() {
        return null;
    }

    @Override
    public IRepositorioConta criarRepositorioConta() {
        return null;
    }
}
