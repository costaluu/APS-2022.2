package com.ufpe.aps.conta;

import com.ufpe.aps.carrinho.Carrinho;

import java.util.List;

public interface IRepositorioConta {

    List<RegistroConta> getAll();

    boolean checarExistencia(String login);

    void criarConta(String login, String senha);

    RegistroConta pegarConta(String login);

    void atualizarCarrinho(String login, Carrinho carrinho);

    void deletarConta(RegistroConta registroConta);
}
