package com.ufpe.aps.controlador;

import com.ufpe.aps.entity.Conta;
import com.ufpe.aps.repository.IRepository.IRepositorioConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ControladorLogin {
    @Autowired
    private IRepositorioConta repositorioConta;

    public boolean efetuarLogin(String login, String senha) {
        Optional<Conta> conta = repositorioConta.checarExistencia(login);
        return conta.map(value -> value.getSenha().equals(senha)).orElse(false);
    }
}
