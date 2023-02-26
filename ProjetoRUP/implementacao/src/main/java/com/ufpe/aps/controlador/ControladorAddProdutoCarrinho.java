package com.ufpe.aps.controlador;

import com.ufpe.aps.entity.Produto;
import com.ufpe.aps.repository.interfaces.IRepositorioConta;
import com.ufpe.aps.repository.interfaces.IRepositorioProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControladorAddProdutoCarrinho {

    @Autowired
    IRepositorioProduto repositorioProduto;

    @Autowired
    IRepositorioConta repositorioConta;

    public void addProdutoCarrinho(Produto produtoDTO) {
        Produto produto = repositorioProduto.pegarProduto(produtoDTO.getId());

//        repositorioConta
    }
}
