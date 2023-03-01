package com.ufpe.aps.controlador;

import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import com.ufpe.aps.repository.IRepositorioConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControladorCadastrar {

    private final IRepositorioConta repositorioConta;

    @Autowired
    public ControladorCadastrar(IRepositorioConta repositorioConta) {
        this.repositorioConta = repositorioConta;
    }

    public void cadastrarConta(String login, String senha) throws AccountAlreadyRegisteredException {
        if(!repositorioConta.checarExistencia(login))
            repositorioConta.criarConta(login, senha);
        else
            throw new AccountAlreadyRegisteredException();
    }
}
