package com.ufrn.imd.ponto_certo.exception;

/**
 * UnauthorizedException
 * Uma exceção para indicar que um usuário não está autorizado a realizar uma operação.
 */
public class UnauthorizedException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Usuário não autorizado.";

    public UnauthorizedException() {
        super(DEFAULT_MESSAGE);
    }

    public UnauthorizedException(String message) {
        super(message);
    }
    
}
