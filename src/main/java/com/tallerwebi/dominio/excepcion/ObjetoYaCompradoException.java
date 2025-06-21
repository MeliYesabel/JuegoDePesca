package com.tallerwebi.dominio.excepcion;

public class ObjetoYaCompradoException extends RuntimeException {
    public ObjetoYaCompradoException(String mensaje) {
        super(mensaje);
    }
}
