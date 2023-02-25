package com.ufpe.aps.factory.impl;

import com.ufpe.aps.factory.FabricaAbstrataRepositorios;
import com.ufpe.aps.repository.IRepository.IRepositorioConta;
import com.ufpe.aps.repository.IRepository.IRepositorioPedido;
import com.ufpe.aps.repository.IRepository.IRepositorioProduto;
import com.ufpe.aps.repository.impl.RepositorioContaBDR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FabricaRepositoriosBDR implements FabricaAbstrataRepositorios {
    @Autowired
    RepositorioContaBDR repositorioContaBDR;

    @Override
    public IRepositorioProduto criarProdutoRepository() {
        return null;
    }

    @Override
    public IRepositorioPedido criarPedidoRepository() {
        return null;
    }

    @Override
    public IRepositorioConta criarRepositorioConta() {
        return repositorioContaBDR;
    }
}
