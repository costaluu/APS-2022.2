package com.ufpe.aps.conta;

import com.ufpe.aps.carrinho.Carrinho;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CadastroConta {

    private final IRepositorioConta repositorioConta;


    public CadastroConta(IRepositorioConta repositorioConta) {
        this.repositorioConta = repositorioConta;
    }

    public List<Conta> getAll() {
        return this.repositorioConta.getAll();
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
