package com.tallerwebi.dominio;

public class ParametroInvalidoException extends RuntimeException {
    public ParametroInvalidoException(String mensaje) {
        super(mensaje);
    }
}
