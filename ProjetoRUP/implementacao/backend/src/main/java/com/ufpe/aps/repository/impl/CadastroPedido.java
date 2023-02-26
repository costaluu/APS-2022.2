package com.ufpe.aps.repository.impl;

import com.ufpe.aps.entity.Carrinho;
import com.ufpe.aps.entity.Pedido;
import com.ufpe.aps.entity.ProdutoParaCarrinho;
import com.ufpe.aps.repository.interfaces.IRepositorioPedido;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CadastroPedido implements IRepositorioPedido {

    private Map<Integer, Pedido> pedidos;

    public CadastroPedido() {
        pedidos = new HashMap<>();
    }

    @Override
    public Pedido criarPedido(String login, Carrinho carrinho) {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(pedidos.size() + 1);
        pedido.setLoginCliente(login);
        List<ProdutoParaCarrinho> produtos = new ArrayList<>(carrinho.getProdutos().values());
        double valorDaCompra = 0.0;
        for(ProdutoParaCarrinho elem : produtos){
            valorDaCompra += elem.getQuantidade() * elem.getProduto().getValor();
        }
        pedido.setValorDaCompra(valorDaCompra);
        pedido.setStatusPedido("Em andamento");
        return pedido;
    }

    @Override
    public void confirmarPedido(String login, Pedido pedido) {
        pedido.setStatusPedido("Confirmado");
        pedidos.put(pedido.getIdPedido(), pedido);
    }
}
