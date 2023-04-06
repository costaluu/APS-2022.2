package com.ufpe.aps.exception;

public class QuantidadeProdutoException extends Exception{
    public QuantidadeProdutoException(String message) {
        super(message);
    }

    public QuantidadeProdutoException() {
        super("Quantidade de produto inválida");
    }

    public QuantidadeProdutoException(Throwable cause) {
        super(cause);
    }

    public QuantidadeProdutoException(String message, Throwable cause) {
        super(message, cause);
    }
}
