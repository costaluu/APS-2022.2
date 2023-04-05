package com.ufpe.aps.conta;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import com.ufpe.aps.exception.AccountNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegistroConta implements IRegistroConta {

    private final IRepositorioConta repositorioConta;

    public RegistroConta(IRepositorioConta repositorioConta) {
        this.repositorioConta = repositorioConta;
    }

    @Override
    public Carrinho pegarCarrinho(String login) throws AccountNotFoundException {
        return this.repositorioConta.pegarConta(login).getCarrinho();
    }

    @Override
    public void atualizarCarrinho(String login, Carrinho carrinho) throws AccountNotFoundException {
        this.repositorioConta.atualizarCarrinho(login, carrinho);
    }

    @Override
    public void esvaziarCarrinho(String login) throws AccountNotFoundException {
        this.repositorioConta.atualizarCarrinho(login, new Carrinho());
    }

    @Override
    public void efetuarCadastro(Conta conta) throws AccountAlreadyRegisteredException {
        if(this.checarExistencia(conta.getLogin()))
            throw new AccountAlreadyRegisteredException();

        this.repositorioConta.criarConta(conta.getLogin(), conta.getSenha());
    }

    @Override
    public void deletarConta(Conta conta) throws AccountNotFoundException {
        this.repositorioConta.deletarConta(conta);
    }

    private boolean checarExistencia(String login) {
        return this.repositorioConta.checarExistencia(login);
    }

    public Conta pegarConta(String login) throws AccountNotFoundException {
        return this.repositorioConta.pegarConta(login);
    }

    @Override
    public List<Conta> getAll() {
        return this.repositorioConta.getAll();
    }
}
