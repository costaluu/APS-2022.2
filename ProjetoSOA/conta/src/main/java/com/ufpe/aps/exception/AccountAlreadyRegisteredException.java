package com.ufpe.aps.exception;

public class AccountAlreadyRegisteredException extends Exception {
    public AccountAlreadyRegisteredException(String message) {
        super(message);
    }

    public AccountAlreadyRegisteredException() {
        super("RegistroConta já cadastrada");
    }

    public AccountAlreadyRegisteredException(Throwable cause) {
        super(cause);
    }

    public AccountAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }
}
