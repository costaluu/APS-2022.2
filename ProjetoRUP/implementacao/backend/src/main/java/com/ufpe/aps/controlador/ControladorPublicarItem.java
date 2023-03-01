package com.ufpe.aps.controlador;

import com.ufpe.aps.entity.Produto;
import com.ufpe.aps.repository.IRepositorioProduto;
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
