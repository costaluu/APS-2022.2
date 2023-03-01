package com.ufpe.aps.controlador;

import com.ufpe.aps.entity.Conta;
import com.ufpe.aps.repository.IRepositorioConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControladorLogin {

    private final IRepositorioConta repositorioConta;

    @Autowired
    public ControladorLogin(IRepositorioConta repositorioConta) {
        this.repositorioConta = repositorioConta;
    }

    public boolean efetuarLogin(String login, String senha) {
        if(repositorioConta.checarExistencia(login)){
            Conta conta = repositorioConta.pegarConta(login);
            return conta.getSenha().equals(senha);
        }
        return false;
    }
}
