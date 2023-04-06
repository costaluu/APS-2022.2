package com.ufpe.aps.factory.impl;

import com.ufpe.aps.conta.IRepositorioConta;
import com.ufpe.aps.factory.FabricaAbstrataRepositorios;
import com.ufpe.aps.repository.bdr.CadastroContaBDR;
import com.ufpe.aps.repository.bdr.ContaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FabricaRepositorioBDR implements FabricaAbstrataRepositorios {

    private final ContaDAO repository;

    public FabricaRepositorioBDR(ContaDAO repository) {
        this.repository = repository;
    }

    @Override
    public IRepositorioConta criarRepositorioConta() {
        return new CadastroContaBDR(repository);
    }
}
