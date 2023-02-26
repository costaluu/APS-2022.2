package com.ufpe.aps.factory.impl;

import com.ufpe.aps.factory.FabricaAbstrataRepositorios;
import com.ufpe.aps.repository.interfaces.IRepositorioConta;
import com.ufpe.aps.repository.interfaces.IRepositorioPedido;
import com.ufpe.aps.repository.interfaces.IRepositorioProduto;
import com.ufpe.aps.repository.impl.CadastroConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FabricaRepositoriosBDR implements FabricaAbstrataRepositorios {
    @Autowired
    CadastroConta cadastroConta;

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
        return cadastroConta;
    }
}
