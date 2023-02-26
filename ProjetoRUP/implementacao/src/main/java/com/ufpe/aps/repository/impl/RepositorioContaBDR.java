package com.ufpe.aps.repository.impl;

import com.ufpe.aps.entity.Carrinho;
import com.ufpe.aps.entity.Conta;
import com.ufpe.aps.repository.interfaces.IRepositorioConta;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RepositorioContaBDR implements IRepositorioConta {

    private List<Conta> contas;

    public RepositorioContaBDR() {
        contas = new ArrayList<>();
    }

    @Override
    public boolean checarExistencia(String login) {
        Optional<Conta> conta = contas.stream().filter(c -> c.getLogin().equals(login)).findFirst();
        return conta.isPresent();
    }

    @Override
    public void criarConta(String login, String senha) {
        Conta conta = new Conta();
        conta.setLogin(login);
        conta.setSenha(senha);
        contas.add(conta);
    }

    @Override
    public Conta pegarConta(String login) {
        Optional<Conta> conta = contas.stream().filter(c -> c.getLogin().equals(login)).findFirst();
        return conta.orElse(null);
    }

    @Override
    public void atualizarCarrinho(String login, Carrinho carrinho) {

    }
}
