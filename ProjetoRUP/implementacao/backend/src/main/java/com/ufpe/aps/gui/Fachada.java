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

    private final ControladorCadastrar controladorCadastrar;

    private final ControladorLogin controladorLogin;

    private final ControladorAddProdutoCarrinho controladorAddProdutoCarrinho;

    private final ControladorPublicarItem controladorPublicarItem;

    private final ControladorCheckout controladorCheckout;

    @Autowired
    public Fachada(ControladorCadastrar controladorCadastrar, ControladorLogin controladorLogin,
                   ControladorAddProdutoCarrinho controladorAddProdutoCarrinho,
                   ControladorPublicarItem controladorPublicarItem, ControladorCheckout controladorCheckout) {
        this.controladorCadastrar = controladorCadastrar;
        this.controladorLogin = controladorLogin;
        this.controladorAddProdutoCarrinho = controladorAddProdutoCarrinho;
        this.controladorPublicarItem = controladorPublicarItem;
        this.controladorCheckout = controladorCheckout;
    }

    public void publicarItem(Produto itemDTO) {
        controladorPublicarItem.publicarItem(itemDTO);
    }

    public void addProdutoCarrinho(AddProdutoCarrinhoDTO produtoDTO) throws Exception {
        controladorAddProdutoCarrinho.addProdutoCarrinho(produtoDTO.getLogin(), produtoDTO.getIdProduto(), produtoDTO.getQuantidade());
    }

    public boolean efetuarLogin(String login, String senha) {
        return controladorLogin.efetuarLogin(login, senha);
    }

    public void realizarPagamento(PagamentoDTO pagamentoDTO) throws HttpClientErrorException {
        controladorCheckout.realizarPagamento(pagamentoDTO);
    }

    public void cadastrarConta(String login, String senha) throws AccountAlreadyRegisteredException {
        controladorCadastrar.cadastrarConta(login, senha);
    }
}
