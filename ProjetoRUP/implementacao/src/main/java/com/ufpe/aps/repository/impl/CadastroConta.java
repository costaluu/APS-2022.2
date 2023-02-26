package com.ufpe.aps.repository.impl;

import com.ufpe.aps.entity.Carrinho;
import com.ufpe.aps.entity.Conta;
import com.ufpe.aps.repository.interfaces.IRepositorioConta;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CadastroConta implements IRepositorioConta {

    private Map<String, Conta> contas;

    public CadastroConta() {
        contas = new HashMap<>();
        contas.put("teste1", new Conta("teste1", "teste1", new Carrinho()));
        contas.put("teste2", new Conta("teste2", "teste2", new Carrinho()));
    }

    @Override
    public List<Conta> getAll() {
        return new ArrayList<>(contas.values());
    }

    @Override
    public boolean checarExistencia(String login) {
        return contas.get(login) != null;
    }

    @Override
    public void criarConta(String login, String senha) {
        Conta conta = new Conta();
        conta.setLogin(login);
        conta.setSenha(senha);
        conta.setCarrinho(new Carrinho());
        contas.put(login, conta);
    }

    @Override
    public Conta pegarConta(String login) {
        return contas.get(login);
    }

    @Override
    public void atualizarCarrinho(String login, Carrinho carrinho) {
        Conta conta = pegarConta(login);
        conta.setCarrinho(carrinho);
        contas.remove(login);
        contas.put(login, conta);
    }

}
