package com.ufpe.aps.factory.impl;

import com.ufpe.aps.factory.FabricaAbstrataRepositorios;
import com.ufpe.aps.repository.inmemory.CadastroProdutoRepositoryInMemory;
import com.ufpe.aps.produto.IRepositorioProduto;
import org.springframework.stereotype.Component;

@Component
public class FabricaRepositoriosInMemory implements FabricaAbstrataRepositorios {

    @Override
    public IRepositorioProduto criarRepositorioProduto() {
        return CadastroProdutoRepositoryInMemory.getInstance();
    }
}
