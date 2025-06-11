package com.tallerwebi.dominio;

public class ObjetoInexistenteException extends RuntimeException {
    public ObjetoInexistenteException(String mensaje) {
        super(mensaje);
    }
}
