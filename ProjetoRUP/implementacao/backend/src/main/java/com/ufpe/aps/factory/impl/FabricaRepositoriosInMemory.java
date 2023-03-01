package com.ufpe.aps.factory.impl;

import com.ufpe.aps.factory.FabricaAbstrataRepositorios;
import com.ufpe.aps.repository.inmemory.CadastroPedido;
import com.ufpe.aps.repository.inmemory.CadastroProduto;
import com.ufpe.aps.repository.IRepositorioConta;
import com.ufpe.aps.repository.IRepositorioPedido;
import com.ufpe.aps.repository.IRepositorioProduto;
import com.ufpe.aps.repository.inmemory.CadastroConta;
import org.springframework.stereotype.Component;

@Component
public class FabricaRepositoriosInMemory implements FabricaAbstrataRepositorios {

    @Override
    public IRepositorioProduto criarRepositorioProduto() {
        return CadastroProduto.getInstance();
    }

    @Override
    public IRepositorioPedido criarRepositorioPedido() {
        return CadastroPedido.getInstance();
    }

    @Override
    public IRepositorioConta criarRepositorioConta() {
        System.out.println("Criando repositorio de conta");
        return CadastroConta.getInstance();
    }
}
