package com.ufpe.aps.gui;

import com.ufpe.aps.controladores.*;
import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import com.ufpe.aps.pojo.AddProdutoCarrinhoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Fachada {

    private final ControladorCadastrar controladorCadastrar;

    private final ControladorLogin controladorLogin;

    private final ControladorAddProdutoCarrinho controladorAddProdutoCarrinho;

    @Autowired
    public Fachada(ControladorCadastrar controladorCadastrar, ControladorLogin controladorLogin, ControladorAddProdutoCarrinho controladorAddProdutoCarrinho) {
        this.controladorCadastrar = controladorCadastrar;
        this.controladorLogin = controladorLogin;
        this.controladorAddProdutoCarrinho = controladorAddProdutoCarrinho;

    }

    public boolean efetuarLogin(String login, String senha) {
        return controladorLogin.efetuarLogin(login, senha);
    }

    public void cadastrarConta(String login, String senha) throws AccountAlreadyRegisteredException {
        controladorCadastrar.cadastrarConta(login, senha);
    }

    public void addProdutoCarrinho(AddProdutoCarrinhoDTO produtoDTO) throws Exception {
        controladorAddProdutoCarrinho.addProdutoCarrinho(produtoDTO.getLogin(), produtoDTO.getIdProduto(), produtoDTO.getQuantidade());
    }
}
