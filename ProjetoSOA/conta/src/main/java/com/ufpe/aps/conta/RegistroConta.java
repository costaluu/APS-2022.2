package com.ufpe.aps.conta;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import org.springframework.stereotype.Component;

@Component
public class RegistroConta implements IRegistroConta {

    private final IRepositorioConta repositorioConta;

    public RegistroConta(IRepositorioConta repositorioConta) {
        this.repositorioConta = repositorioConta;
    }

    @Override
    public Carrinho pegarCarrinho(String login) {
        return this.repositorioConta.pegarConta(login).getCarrinho();
    }

    @Override
    public void atualizarCarrinho(String login, Carrinho carrinho) {
        this.repositorioConta.atualizarCarrinho(login, carrinho);
    }

    @Override
    public void esvaziarCarrinho(String login) {
        this.repositorioConta.atualizarCarrinho(login, new Carrinho());
    }

    @Override
    public void efetuarCadastro(Conta conta) throws AccountAlreadyRegisteredException {
        if(this.checarExistencia(conta.getLogin()))
            throw new AccountAlreadyRegisteredException();

        this.repositorioConta.criarConta(conta.getLogin(), conta.getSenha());
    }

    @Override
    public void deletarConta(Conta conta) {
        if(!this.checarExistencia(conta.getLogin()))
            throw new IllegalArgumentException("Conta não existe");

        Conta contaDoRepositorio = this.pegarConta(conta.getLogin());
        if(!contaDoRepositorio.getSenha().equals(conta.getSenha()))
            throw new IllegalArgumentException("Senha incorreta");

        this.repositorioConta.deletarConta(contaDoRepositorio);
    }

    private boolean checarExistencia(String login) {
        return this.repositorioConta.checarExistencia(login);
    }

    private Conta pegarConta(String login) {
        return this.repositorioConta.pegarConta(login);
    }
}
