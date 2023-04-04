package com.ufpe.aps.conta;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import com.ufpe.aps.exception.AccountNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ServicoConta implements IServicoConta {

    private final IRegistroConta registroConta;

    public ServicoConta(IRegistroConta registroConta) {
        this.registroConta = registroConta;
    }

    @Override
    public void efetuarCadastro(Conta conta) throws AccountAlreadyRegisteredException {
        this.registroConta.efetuarCadastro(conta);
    }

    @Override
    public void efetuarLogin(Conta conta) throws AccountNotFoundException {

    }

    @Override
    public void deletarConta(Conta conta) {
        this.registroConta.deletarConta(conta);
    }

}
