package com.ufpe.aps.factory;

import com.ufpe.aps.conta.IRepositorioConta;
import com.ufpe.aps.pedido.IRepositorioPedido;
import com.ufpe.aps.produto.IRepositorioProduto;

public interface FabricaAbstrataRepositorios {
    public IRepositorioProduto criarRepositorioProduto();
    public IRepositorioPedido criarRepositorioPedido();
    public IRepositorioConta criarRepositorioConta();
}
