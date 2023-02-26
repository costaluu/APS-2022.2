package com.ufpe.aps.repository.impl;

import com.ufpe.aps.entity.Carrinho;
import com.ufpe.aps.entity.Pedido;
import com.ufpe.aps.repository.interfaces.IRepositorioPedido;
import org.springframework.stereotype.Component;

@Component
public class RepositorioPedidoBDR implements IRepositorioPedido {

    @Override
    public Pedido criarPedido(String login, Carrinho carrinho) {
        return null;
    }

    @Override
    public void confirmarPedido(String login, Pedido pedido) {

    }
}
