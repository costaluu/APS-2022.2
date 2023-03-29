package com.ufpe.aps.gui;

import com.ufpe.aps.controladores.*;
import com.ufpe.aps.produto.Produto;
import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import com.ufpe.aps.pojo.AddProdutoCarrinhoDTO;
import com.ufpe.aps.pojo.PagamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class Fachada {

    private final ControladorPublicarItem controladorPublicarItem;

    @Autowired
    public Fachada(ControladorPublicarItem controladorPublicarItem) {
        this.controladorPublicarItem = controladorPublicarItem;
    }

    public void publicarItem(Produto itemDTO) {
        controladorPublicarItem.publicarItem(itemDTO);
    }
}
