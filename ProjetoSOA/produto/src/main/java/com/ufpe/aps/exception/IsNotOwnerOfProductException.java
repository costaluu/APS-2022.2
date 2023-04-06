package com.ufpe.aps.exception;

public class IsNotOwnerOfProductException extends Exception {
    public IsNotOwnerOfProductException(String message) {
        super(message);
    }

    public IsNotOwnerOfProductException() {
        super("Você não é o dono deste produto");
    }

    public IsNotOwnerOfProductException(Throwable cause) {
        super(cause);
    }

    public IsNotOwnerOfProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
