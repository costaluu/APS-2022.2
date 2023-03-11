package com.ufpe.aps.pedido;

import com.ufpe.aps.carrinho.Carrinho;

public interface IRepositorioPedido {

    public Pedido criarPedido(String login, Carrinho carrinho);

    public void confirmarPedido(String login, Pedido pedido);

}
