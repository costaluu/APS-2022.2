package com.ufpe.aps.conta;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.exception.AccountNotFoundException;

import java.util.List;

public interface IRepositorioConta {

    List<Conta> getAll();

    boolean checarExistencia(String login);

    void criarConta(String login, String senha);

    Conta pegarConta(String login) throws AccountNotFoundException;

    void atualizarCarrinho(String login, Carrinho carrinho) throws AccountNotFoundException;

    void deletarConta(Conta conta);
}
