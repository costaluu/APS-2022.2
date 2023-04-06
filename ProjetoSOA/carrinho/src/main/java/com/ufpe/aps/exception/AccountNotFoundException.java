package com.ufpe.aps.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException() {
        super("Conta não encontrada");
    }

    public AccountNotFoundException(Throwable cause) {
        super(cause);
    }

    public AccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
