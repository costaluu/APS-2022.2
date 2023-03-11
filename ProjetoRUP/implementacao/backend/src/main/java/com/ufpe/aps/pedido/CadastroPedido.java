package com.ufpe.aps.pedido;

import com.ufpe.aps.carrinho.Carrinho;
import org.springframework.stereotype.Component;

@Component
public class CadastroPedido {

    private final IRepositorioPedido repositorioPedido;

    public CadastroPedido(IRepositorioPedido repositorioPedido) {
        this.repositorioPedido = repositorioPedido;
    }

    public Pedido criarPedido(String login, Carrinho carrinho) {
        return repositorioPedido.criarPedido(login, carrinho);
    }

    public void confirmarPedido(String login, Pedido pedido) {
        repositorioPedido.confirmarPedido(login, pedido);
    }
}
