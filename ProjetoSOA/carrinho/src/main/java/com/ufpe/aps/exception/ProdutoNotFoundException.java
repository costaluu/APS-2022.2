package com.ufpe.aps.exception;

public class ProdutoNotFoundException extends RuntimeException {
    public ProdutoNotFoundException(String message) {
        super(message);
    }

    public ProdutoNotFoundException() {
        super("Produto não encontrado");
    }

    public ProdutoNotFoundException(Throwable cause) {
        super(cause);
    }

    public ProdutoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
