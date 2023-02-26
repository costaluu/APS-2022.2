package com.ufpe.aps.controlador;

import com.ufpe.aps.entity.Produto;
import com.ufpe.aps.repository.interfaces.IRepositorioProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControladorPublicarItem {

    @Autowired
    IRepositorioProduto repositorioProduto;

    public void publicarItem(Produto produtoDTO) {
        repositorioProduto.addAoEstoque(produtoDTO);
    }
}
