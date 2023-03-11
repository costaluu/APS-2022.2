package com.ufpe.aps.repository.inmemory;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.pedido.Pedido;
import com.ufpe.aps.produtoparacarrinho.ProdutoParaCarrinho;
import com.ufpe.aps.pedido.IRepositorioPedido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CadastroPedidoRepositoryInMemory implements IRepositorioPedido {

    private static CadastroPedidoRepositoryInMemory instance;

    public static CadastroPedidoRepositoryInMemory getInstance() {
        if (instance == null) {
            instance = new CadastroPedidoRepositoryInMemory();
        }
        return instance;
    }

    private Map<Integer, Pedido> pedidos;

    public CadastroPedidoRepositoryInMemory() {
        pedidos = new HashMap<>();
        System.out.println("Pedidos criados: " + pedidos.size());
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
