package com.ufpe.aps.controladores;

import com.ufpe.aps.produto.Produto;
import com.ufpe.aps.produto.IRepositorioProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControladorPublicarItem {

    private final IRepositorioProduto repositorioProduto;

    @Autowired
    public ControladorPublicarItem(IRepositorioProduto repositorioProduto) {
        this.repositorioProduto = repositorioProduto;
    }

    public void publicarItem(Produto produtoDTO) {
        repositorioProduto.addAoEstoque(produtoDTO);
    }
}
