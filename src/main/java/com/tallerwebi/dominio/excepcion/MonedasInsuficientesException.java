package com.tallerwebi.dominio.excepcion;

public class MonedasInsuficientesException extends RuntimeException {
    public MonedasInsuficientesException(String mensaje) {
        super(mensaje);
    }
}
