package com.ufpe.aps.factory;

import com.ufpe.aps.repository.IRepositorioConta;
import com.ufpe.aps.repository.IRepositorioPedido;
import com.ufpe.aps.repository.IRepositorioProduto;

public interface FabricaAbstrataRepositorios {
    public IRepositorioProduto criarRepositorioProduto();
    public IRepositorioPedido criarRepositorioPedido();
    public IRepositorioConta criarRepositorioConta();
}
