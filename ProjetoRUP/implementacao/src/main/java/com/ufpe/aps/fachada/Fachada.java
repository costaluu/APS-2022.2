package com.ufpe.aps.fachada;

import com.ufpe.aps.controlador.ControladorCadastrar;
import com.ufpe.aps.controlador.ControladorLogin;
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

    public void publicarItem(String login, String nome, String descricao, int quantidade, double valor) {

    }

    public void addProdutoCarrinho(String idProduto, String login, int quantidade) {

    }

    public boolean efetuarLogin(String login, String senha) {
        return controladorLogin.efetuarLogin(login, senha);
    }

    public void realizarPagamento(String login, String numCartao, int codSeguranca, String validade, String nomeNoCartao) {

    }

    public void cadastrarConta(String login, String senha) {
        controladorCadastrar.cadastrarConta(login, senha);
    }
}
