package com.ufpe.aps.factory.impl;

import com.ufpe.aps.factory.FabricaAbstrataRepositorios;
import com.ufpe.aps.repository.inmemory.CadastroContaRepositoryInMemory;
import com.ufpe.aps.conta.IRepositorioConta;
import org.springframework.stereotype.Component;

@Component
public class FabricaRepositoriosInMemory implements FabricaAbstrataRepositorios {

    @Override
    public IRepositorioConta criarRepositorioConta() {
        System.out.println("Criando repositorio de conta");
        return CadastroContaRepositoryInMemory.getInstance();
    }
}
