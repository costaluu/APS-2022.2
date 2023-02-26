package com.ufpe.aps.repository.interfaces;

import com.ufpe.aps.entity.Carrinho;
import com.ufpe.aps.entity.Conta;

public interface IRepositorioConta {

    public boolean checarExistencia(String login);

    public void criarConta(String login, String senha);

    public Conta pegarConta(String login);

    public void atualizarCarrinho(String login, Carrinho carrinho);
}
