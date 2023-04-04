package com.ufpe.aps.factory.impl;

import com.ufpe.aps.conta.IRepositorioConta;
import com.ufpe.aps.factory.FabricaAbstrataRepositorios;
import com.ufpe.aps.repository.mongo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FabricaRepositorioMongo implements FabricaAbstrataRepositorios {

    @Autowired
    private ContaMongoRepository contaMongoRepository;

    @Override
    @Autowired
    public IRepositorioConta criarRepositorioConta() {
        System.out.println("Fabrica criando repositorio de conta em mongo");
        return new CadastroContaMongo(contaMongoRepository);
    }
}
