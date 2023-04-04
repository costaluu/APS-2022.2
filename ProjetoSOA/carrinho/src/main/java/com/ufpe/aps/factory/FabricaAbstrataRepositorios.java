package com.ufpe.aps.factory;

import com.ufpe.aps.conta.IRepositorioConta;

public interface FabricaAbstrataRepositorios {
    public IRepositorioConta criarRepositorioConta();
}
