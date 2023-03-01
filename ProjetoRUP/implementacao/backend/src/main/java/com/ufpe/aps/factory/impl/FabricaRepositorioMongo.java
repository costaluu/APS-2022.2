package com.ufpe.aps.factory.impl;

import com.ufpe.aps.factory.FabricaAbstrataRepositorios;
import com.ufpe.aps.repository.IRepositorioConta;
import com.ufpe.aps.repository.IRepositorioPedido;
import com.ufpe.aps.repository.IRepositorioProduto;

public class FabricaRepositorioMongo implements FabricaAbstrataRepositorios {
    @Override
    public IRepositorioProduto criarRepositorioProduto() {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public IRepositorioPedido criarRepositorioPedido() {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public IRepositorioConta criarRepositorioConta() {
        throw new RuntimeException("Not implemented yet");
    }
}
