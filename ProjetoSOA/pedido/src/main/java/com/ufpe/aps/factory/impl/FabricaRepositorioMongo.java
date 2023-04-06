package com.ufpe.aps.factory.impl;

import com.ufpe.aps.conta.IRepositorioConta;
import com.ufpe.aps.factory.FabricaAbstrataRepositorios;
import com.ufpe.aps.pedido.IRepositorioPedido;
import com.ufpe.aps.produto.IRepositorioProduto;
import com.ufpe.aps.repository.mongo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FabricaRepositorioMongo implements FabricaAbstrataRepositorios {

    @Autowired
    private ContaMongoRepository contaMongoRepository;

    @Autowired
    private PedidoMongoRepository pedidoMongoRepository;

    @Autowired
    private ProdutoMongoRepository produtoMongoRepository;

    @Override
    @Autowired
    public IRepositorioProduto criarRepositorioProduto() {
        System.out.println("Fabrica criando repositorio de produto em mongo");
        return new CadastroProdutoMongo(produtoMongoRepository);
    }

    @Override
    public IRepositorioPedido criarRepositorioPedido() {
        System.out.println("Fabrica criando repositorio de pedido em mongo");
        return new CadastroPedidoMongo(pedidoMongoRepository);
    }

    @Override
    @Autowired
    public IRepositorioConta criarRepositorioConta() {
        System.out.println("Fabrica criando repositorio de conta em mongo");
        return new CadastroContaMongo(contaMongoRepository);
    }
}
