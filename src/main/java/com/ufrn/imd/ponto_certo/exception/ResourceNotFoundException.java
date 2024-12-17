package com.ufrn.imd.ponto_certo.exception;

/**
 * ResourceNotFoundException
 * Uma exceção para indicar que um recurso não foi encontrado.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}