package com.ufpe.aps.conta;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.exception.AccountAlreadyRegisteredException;

public interface IRegistroConta {

    Carrinho pegarCarrinho(String login);

    void atualizarCarrinho(String login, Carrinho carrinho);

    void esvaziarCarrinho(String login);

    void efetuarCadastro(Conta conta) throws AccountAlreadyRegisteredException;

    void deletarConta(Conta conta);
}
