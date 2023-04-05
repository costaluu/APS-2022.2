package com.ufpe.aps.conta;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import com.ufpe.aps.exception.AccountNotFoundException;
import com.ufpe.aps.factory.impl.FabricaRepositorioBDR;
import com.ufpe.aps.factory.impl.FabricaRepositoriosInMemory;
import com.ufpe.aps.repository.bdr.ContaDAO;
import org.springframework.core.env.Environment;

import java.util.List;

public class FachadaConta implements IRegistroConta, IServicoConta {

    private final IRegistroConta registroConta;

    private final IServicoConta servicoConta;

    public FachadaConta(Environment env, ContaDAO repository) {
        String choice = env.getProperty("fabrica.repositorios.choice") != null ? env.getProperty("fabrica.repositorios.choice") : "INMEMORY";
        assert choice != null;
        IRepositorioConta repo = choice.equalsIgnoreCase("bdr") ?
                new FabricaRepositorioBDR(repository).criarRepositorioConta() : new FabricaRepositoriosInMemory().criarRepositorioConta();
        this.registroConta = new RegistroConta(repo);
        this.servicoConta = new ServicoConta(this.registroConta);
    }

    @Override
    public Carrinho pegarCarrinho(String login) throws AccountNotFoundException {
        return this.registroConta.pegarCarrinho(login);
    }

    @Override
    public void atualizarCarrinho(String login, Carrinho carrinho) throws AccountNotFoundException {
        this.registroConta.atualizarCarrinho(login, carrinho);
    }

    @Override
    public void esvaziarCarrinho(String login) throws AccountNotFoundException {
        this.registroConta.esvaziarCarrinho(login);
    }

    @Override
    public void efetuarCadastro(Conta conta) throws AccountAlreadyRegisteredException {
        this.registroConta.efetuarCadastro(conta);
    }

    @Override
    public void efetuarLogin(Conta conta) throws AccountNotFoundException {
        this.servicoConta.efetuarLogin(conta);
    }

    @Override
    public void deletarConta(Conta conta) throws AccountNotFoundException {
        this.registroConta.deletarConta(conta);
    }

    @Override
    public Conta pegarConta(String login) throws AccountNotFoundException {
        return this.registroConta.pegarConta(login);
    }

    @Override
    public List<Conta> getAll() {
        return this.registroConta.getAll();
    }
}
