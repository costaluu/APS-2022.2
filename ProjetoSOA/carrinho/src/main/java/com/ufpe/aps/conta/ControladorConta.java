package com.ufpe.aps.conta;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import com.ufpe.aps.exception.AccountNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ControladorConta {

    private final IRepositorioConta repositorioConta;


    public ControladorConta(IRepositorioConta repositorioConta) {
        this.repositorioConta = repositorioConta;
    }

    public void efetuarLogin(Conta conta) throws AccountNotFoundException {
        if(!this.checarExistencia(conta.getLogin()))
            throw new AccountNotFoundException();

        Conta contaDoRepositorio = this.pegarConta(conta.getLogin());
        if(!contaDoRepositorio.getSenha().equals(conta.getSenha()))
            throw new IllegalArgumentException("Senha incorreta");
    }

    public void cadastrarConta(Conta conta) throws AccountAlreadyRegisteredException {
        if(this.checarExistencia(conta.getLogin()))
            throw new AccountAlreadyRegisteredException();

        this.criarConta(conta.getLogin(), conta.getSenha());
    }

    public List<Conta> getAll() {
        List<Conta> contas = new ArrayList<>(this.repositorioConta.getAll());
        return contas.stream().peek(conta -> {
            conta.setSenha("");
            conta.setCarrinho(new Carrinho());
        }).collect(Collectors.toList());
    }

    public boolean checarExistencia(String login) {
        return this.repositorioConta.checarExistencia(login);
    }

    public void criarConta(String login, String senha) {
        this.repositorioConta.criarConta(login, senha);
    }

    public Conta pegarConta(String login) {
        return this.repositorioConta.pegarConta(login);
    }

    public void atualizarCarrinho(String login, Carrinho carrinho) {
        this.repositorioConta.atualizarCarrinho(login, carrinho);
    }
}
