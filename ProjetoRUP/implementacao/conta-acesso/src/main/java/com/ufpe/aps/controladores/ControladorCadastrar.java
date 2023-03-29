package com.ufpe.aps.controladores;

import com.ufpe.aps.conta.CadastroConta;
import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import com.ufpe.aps.conta.IRepositorioConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControladorCadastrar {

    private final CadastroConta cadastroConta;

    @Autowired
    public ControladorCadastrar(CadastroConta cadastroConta) {
        this.cadastroConta = cadastroConta;
    }

    public void cadastrarConta(String login, String senha) throws AccountAlreadyRegisteredException {
        if(!cadastroConta.checarExistencia(login))
            cadastroConta.criarConta(login, senha);
        else
            throw new AccountAlreadyRegisteredException();
    }
}
