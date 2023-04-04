package com.ufpe.aps.conta;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.conta.Conta;

import java.util.List;

public interface IRepositorioConta {

    List<Conta> getAll();

    boolean checarExistencia(String login);

    void criarConta(String login, String senha);

    Conta pegarConta(String login);

    void atualizarCarrinho(String login, Carrinho carrinho);
}
