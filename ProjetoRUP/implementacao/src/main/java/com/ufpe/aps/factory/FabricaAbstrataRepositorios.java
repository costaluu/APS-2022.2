package com.ufpe.aps.factory;

import com.ufpe.aps.repository.IRepository.IRepositorioConta;
import com.ufpe.aps.repository.IRepository.IRepositorioPedido;
import com.ufpe.aps.repository.IRepository.IRepositorioProduto;

public interface FabricaAbstrataRepositorios {
    public IRepositorioProduto criarProdutoRepository();
    public IRepositorioPedido criarPedidoRepository();
    public IRepositorioConta criarRepositorioConta();
}
