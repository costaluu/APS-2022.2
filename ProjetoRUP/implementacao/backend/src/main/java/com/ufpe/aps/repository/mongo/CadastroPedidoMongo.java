package com.ufpe.aps.repository.mongo;

import com.ufpe.aps.entity.Carrinho;
import com.ufpe.aps.entity.Pedido;
import com.ufpe.aps.entity.ProdutoParaCarrinho;
import com.ufpe.aps.repository.IRepositorioPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CadastroPedidoMongo implements IRepositorioPedido {


    private final PedidoMongoRepository pedidoMongoRepository;

    private static int count = 0;

    @Autowired
    public CadastroPedidoMongo(PedidoMongoRepository pedidoMongoRepository) {
        this.pedidoMongoRepository = pedidoMongoRepository;
        System.out.println("Criando CadastroPedidoMongo: " + count++);
    }

    @Override
    public Pedido criarPedido(String login, Carrinho carrinho) {
        List<Pedido> pedidos = pedidoMongoRepository.findAll();
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
        pedidoMongoRepository.save(pedido);
    }
}
