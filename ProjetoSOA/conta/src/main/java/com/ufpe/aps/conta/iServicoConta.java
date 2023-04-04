package com.ufpe.aps.conta;

import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import com.ufpe.aps.exception.AccountNotFoundException;

public interface iServicoConta {

    void efetuarCadastro(RegistroConta registroConta) throws AccountAlreadyRegisteredException;

    void efetuarLogin(RegistroConta registroConta) throws AccountNotFoundException;

    void deletarConta(RegistroConta registroConta);
}
