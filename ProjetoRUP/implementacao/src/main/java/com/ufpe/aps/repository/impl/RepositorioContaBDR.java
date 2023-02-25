package com.ufpe.aps.repository.impl;

import com.ufpe.aps.entity.Conta;
import com.ufpe.aps.repository.db.RepositoryContaBDR;
import com.ufpe.aps.repository.IRepository.IRepositorioConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RepositorioContaBDR implements IRepositorioConta {

    @Autowired
    RepositoryContaBDR repositoryContaBDR;

    public RepositorioContaBDR(){
        System.out.println("RepositorioContaBDR criado");
    }

    @Override
    public List<Conta> pegarTodasContas() {
        return (List<Conta>) repositoryContaBDR.findAll();
    }

    @Override
    public Optional<Conta> checarExistencia(String login) {
        return repositoryContaBDR.findById(login);
    }

    @Override
    public void criarConta(String login, String senha) {
        Conta conta = new Conta();
        conta.setLogin(login);
        conta.setSenha(senha);
        repositoryContaBDR.save(conta);
    }

    @Override
    public Conta pegarConta(String login) {
        return null;
    }
}
