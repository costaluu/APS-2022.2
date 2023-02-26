package com.ufpe.aps.controlador;

import com.ufpe.aps.repository.interfaces.IRepositorioConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControladorCadastrar {
    @Autowired
    private IRepositorioConta repositorioConta;

    public void cadastrarConta(String login, String senha) throws Exception {
        if(!repositorioConta.checarExistencia(login))
            repositorioConta.criarConta(login, senha);
        else
            throw new Exception("Conta já existente");
    }
}
