package com.ufpe.aps.factory;

import com.ufpe.aps.repository.interfaces.IRepositorioConta;
import com.ufpe.aps.repository.interfaces.IRepositorioPedido;
import com.ufpe.aps.repository.interfaces.IRepositorioProduto;

public interface FabricaAbstrataRepositorios {
    public IRepositorioProduto criarProdutoRepository();
    public IRepositorioPedido criarPedidoRepository();
    public IRepositorioConta criarRepositorioConta();
}
