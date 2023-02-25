package com.ufpe.aps.controlador;

import com.ufpe.aps.factory.FabricaAbstrataRepositorios;
import com.ufpe.aps.factory.FactoryCreator;
import com.ufpe.aps.factory.impl.FabricaRepositoriosBDR;
import com.ufpe.aps.repository.IRepository.IRepositorioConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControladorCadastrar {
    @Autowired
    private IRepositorioConta repositorioConta;

    public ControladorCadastrar() {
        String ok = "ok";
    }

    public void cadastrarConta(String login, String senha) {
        repositorioConta.criarConta(login, senha);
    }
}
