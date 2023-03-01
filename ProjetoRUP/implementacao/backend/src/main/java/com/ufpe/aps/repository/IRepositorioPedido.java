package com.ufpe.aps.repository;

import com.ufpe.aps.entity.Carrinho;
import com.ufpe.aps.entity.Pedido;

public interface IRepositorioPedido {

    public Pedido criarPedido(String login, Carrinho carrinho);

    public void confirmarPedido(String login, Pedido pedido);

}
