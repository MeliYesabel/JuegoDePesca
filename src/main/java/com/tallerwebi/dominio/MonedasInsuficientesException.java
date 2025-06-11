package com.tallerwebi.dominio;

public class MonedasInsuficientesException extends RuntimeException {
    public MonedasInsuficientesException(String mensaje) {
        super(mensaje);
    }
}
