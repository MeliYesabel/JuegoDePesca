package com.tallerwebi.dominio.excepcion;

public class ParametroInvalidoException extends RuntimeException {
    public ParametroInvalidoException(String mensaje) {
        super(mensaje);
    }
}
