package com.ufpe.aps.controlador;

import com.ufpe.aps.repository.interfaces.IRepositorioConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControladorLogin {
    @Autowired
    private IRepositorioConta repositorioConta;

    public boolean efetuarLogin(String login, String senha) {
        return repositorioConta.checarExistencia(login);
    }
}
