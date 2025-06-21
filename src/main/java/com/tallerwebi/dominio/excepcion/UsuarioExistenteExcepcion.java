package com.tallerwebi.dominio.excepcion;

public class UsuarioExistenteExcepcion extends RuntimeException{
    public UsuarioExistenteExcepcion(String mensaje) {
        super(mensaje);
    }
}
