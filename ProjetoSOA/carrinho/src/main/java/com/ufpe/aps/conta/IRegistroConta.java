package com.ufpe.aps.conta;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import com.ufpe.aps.exception.AccountNotFoundException;

import java.util.List;

public interface IRegistroConta {

    Carrinho pegarCarrinho(String login) throws AccountNotFoundException;

    void atualizarCarrinho(String login, Carrinho carrinho) throws AccountNotFoundException;

    void esvaziarCarrinho(String login) throws AccountNotFoundException;

    void efetuarCadastro(Conta conta) throws AccountAlreadyRegisteredException;

    void deletarConta(Conta conta) throws AccountNotFoundException;

    Conta pegarConta(String login) throws AccountNotFoundException;

    List<Conta> getAll();
}
