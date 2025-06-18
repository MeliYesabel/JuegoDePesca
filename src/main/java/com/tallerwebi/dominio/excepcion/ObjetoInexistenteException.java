package com.tallerwebi.dominio.excepcion;

public class ObjetoInexistenteException extends RuntimeException {
    public ObjetoInexistenteException(String mensaje) {
        super(mensaje);
    }
}
