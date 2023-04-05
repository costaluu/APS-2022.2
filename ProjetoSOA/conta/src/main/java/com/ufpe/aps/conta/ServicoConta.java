package com.ufpe.aps.conta;

import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import com.ufpe.aps.exception.AccountNotFoundException;
import com.ufpe.aps.factory.impl.FabricaRepositorioBDR;
import com.ufpe.aps.factory.impl.FabricaRepositoriosInMemory;
import com.ufpe.aps.repository.bdr.ContaDAO;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServicoConta implements IServicoConta {

    private final IRegistroConta registroConta;

    public ServicoConta(Environment env, ContaDAO repository) {
//        this.registroConta = registroConta;
        String choice = env.getProperty("fabrica.repositorios.choice") != null ? env.getProperty("fabrica.repositorios.choice") : "INMEMORY";
        assert choice != null;
        IRepositorioConta repo = choice.equalsIgnoreCase("bdr") ?
                new FabricaRepositorioBDR(repository).criarRepositorioConta() : new FabricaRepositoriosInMemory().criarRepositorioConta();
        this.registroConta = new RegistroConta(repo);
    }

    @Override
    public void efetuarCadastro(Conta conta) throws AccountAlreadyRegisteredException {
        this.registroConta.efetuarCadastro(conta);
    }

    @Override
    public void efetuarLogin(Conta conta) throws AccountNotFoundException {
        Conta contaDoRepositorio = this.registroConta.pegarConta(conta.getLogin());
        if(contaDoRepositorio == null || !contaDoRepositorio.getSenha().equals(conta.getSenha()))
            throw new AccountNotFoundException();
    }

    @Override
    public void deletarConta(Conta conta) throws AccountNotFoundException {
        this.registroConta.deletarConta(conta);
    }

    @Override
    public List<Conta> getAll() {
        return this.registroConta.getAll();
    }

}
