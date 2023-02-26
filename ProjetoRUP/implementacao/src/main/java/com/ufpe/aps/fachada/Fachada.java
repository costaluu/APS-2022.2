package com.ufpe.aps.fachada;

import com.ufpe.aps.controlador.*;
import com.ufpe.aps.entity.Produto;
import com.ufpe.aps.pojo.PagamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class Fachada {

    @Autowired
    ControladorCadastrar controladorCadastrar;

    @Autowired
    ControladorLogin controladorLogin;

    @Autowired
    ControladorAddProdutoCarrinho controladorAddProdutoCarrinho;

    @Autowired
    ControladorPublicarItem controladorPublicarItem;

    @Autowired
    ControladorCheckout controladorCheckout;

    public void publicarItem(Produto itemDTO) {
        controladorPublicarItem.publicarItem(itemDTO);
    }

    public void addProdutoCarrinho(Produto produtoDTO) {

    }

    public boolean efetuarLogin(String login, String senha) {
        return controladorLogin.efetuarLogin(login, senha);
    }

    public void realizarPagamento(PagamentoDTO pagamentoDTO) {
        controladorCheckout.realizarPagamento(pagamentoDTO);
    }

    public void cadastrarConta(String login, String senha) {
        controladorCadastrar.cadastrarConta(login, senha);
    }
}
