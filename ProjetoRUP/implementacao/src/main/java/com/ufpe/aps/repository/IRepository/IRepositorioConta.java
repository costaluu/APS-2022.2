package com.ufpe.aps.repository.IRepository;

import com.ufpe.aps.entity.Conta;

import java.util.List;
import java.util.Optional;

public interface IRepositorioConta {

    public List<Conta> pegarTodasContas();

    public Optional<Conta> checarExistencia(String login);

    public void criarConta(String login, String senha);

    public Conta pegarConta(String login);
}
