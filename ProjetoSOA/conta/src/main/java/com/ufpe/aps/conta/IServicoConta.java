package com.ufpe.aps.conta;

import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import com.ufpe.aps.exception.AccountNotFoundException;

public interface IServicoConta {

    void efetuarCadastro(Conta conta) throws AccountAlreadyRegisteredException;

    void efetuarLogin(Conta conta) throws AccountNotFoundException;

    void deletarConta(Conta conta);
}
