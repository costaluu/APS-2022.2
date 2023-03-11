package com.ufpe.aps.factory.impl;

import com.ufpe.aps.factory.FabricaAbstrataRepositorios;
import com.ufpe.aps.repository.inmemory.CadastroContaRepositoryInMemory;
import com.ufpe.aps.repository.inmemory.CadastroPedidoRepositoryInMemory;
import com.ufpe.aps.repository.inmemory.CadastroProdutoRepositoryInMemory;
import com.ufpe.aps.conta.IRepositorioConta;
import com.ufpe.aps.pedido.IRepositorioPedido;
import com.ufpe.aps.produto.IRepositorioProduto;
import com.ufpe.aps.conta.CadastroConta;
import org.springframework.stereotype.Component;

@Component
public class FabricaRepositoriosInMemory implements FabricaAbstrataRepositorios {

    @Override
    public IRepositorioProduto criarRepositorioProduto() {
        return CadastroProdutoRepositoryInMemory.getInstance();
    }

    @Override
    public IRepositorioPedido criarRepositorioPedido() {
        return CadastroPedidoRepositoryInMemory.getInstance();
    }

    @Override
    public IRepositorioConta criarRepositorioConta() {
        System.out.println("Criando repositorio de conta");
        return CadastroContaRepositoryInMemory.getInstance();
    }
}
