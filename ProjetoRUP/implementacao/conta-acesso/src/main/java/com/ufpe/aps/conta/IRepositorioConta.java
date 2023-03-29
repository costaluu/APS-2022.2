package com.ufpe.aps.conta;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.conta.Conta;

import java.util.List;

public interface IRepositorioConta {

    public List<Conta> getAll();

    public boolean checarExistencia(String login);

    public void criarConta(String login, String senha);

    public Conta pegarConta(String login);

    public void atualizarCarrinho(String login, Carrinho carrinho);
}
