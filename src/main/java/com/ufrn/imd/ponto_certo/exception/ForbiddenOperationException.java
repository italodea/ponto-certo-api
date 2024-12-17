package com.ufrn.imd.ponto_certo.exception;

/**
 * ForbiddenOperationException
 * Uma exceção para indicar que um usuário está proibido de realizar uma operação em um recurso que não é seu.
 */
public class ForbiddenOperationException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "O usuário está proibido de realizar uma operação em um recurso que não é seu.";

    public ForbiddenOperationException() {
        super(DEFAULT_MESSAGE);
    }

    public ForbiddenOperationException(String message) {
        super(message);
    }
}
