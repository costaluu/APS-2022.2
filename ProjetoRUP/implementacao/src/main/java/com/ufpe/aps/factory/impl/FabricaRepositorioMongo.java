package com.ufpe.aps.factory.impl;

import com.ufpe.aps.factory.FabricaAbstrataRepositorios;
import com.ufpe.aps.repository.IRepository.IRepositorioConta;
import com.ufpe.aps.repository.IRepository.IRepositorioPedido;
import com.ufpe.aps.repository.IRepository.IRepositorioProduto;

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
