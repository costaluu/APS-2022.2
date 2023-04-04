package com.ufpe.aps.conta;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import com.ufpe.aps.exception.AccountNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ServicoConta implements iServicoConta {

    private final IRepositorioConta repositorioConta;


    public ServicoConta(IRepositorioConta repositorioConta) {
        this.repositorioConta = repositorioConta;
    }

    @Override
    public void efetuarCadastro(RegistroConta registroConta) throws AccountAlreadyRegisteredException {
        if(this.checarExistencia(registroConta.getLogin()))
            throw new AccountAlreadyRegisteredException();

        this.repositorioConta.criarConta(registroConta.getLogin(), registroConta.getSenha());
    }

    @Override
    public void efetuarLogin(RegistroConta registroConta) throws AccountNotFoundException {
        if(!this.checarExistencia(registroConta.getLogin()))
            throw new AccountNotFoundException();

        RegistroConta registroContaDoRepositorio = this.pegarConta(registroConta.getLogin());
        if(!registroContaDoRepositorio.getSenha().equals(registroConta.getSenha()))
            throw new IllegalArgumentException("Senha incorreta");
    }

    @Override
    public void deletarConta(RegistroConta registroConta) {
        if(!this.checarExistencia(registroConta.getLogin()))
            throw new IllegalArgumentException("RegistroConta não existe");

        RegistroConta registroContaDoRepositorio = this.pegarConta(registroConta.getLogin());
        if(!registroContaDoRepositorio.getSenha().equals(registroConta.getSenha()))
            throw new IllegalArgumentException("Senha incorreta");

        this.repositorioConta.deletarConta(registroContaDoRepositorio);
    }

    public List<RegistroConta> getAll() {
        List<RegistroConta> registroContas = new ArrayList<>(this.repositorioConta.getAll());
        return registroContas.stream().peek(conta -> {
            conta.setSenha("");
            conta.setCarrinho(new Carrinho());
        }).collect(Collectors.toList());
    }

    private boolean checarExistencia(String login) {
        return this.repositorioConta.checarExistencia(login);
    }

    private RegistroConta pegarConta(String login) {
        return this.repositorioConta.pegarConta(login);
    }

}
