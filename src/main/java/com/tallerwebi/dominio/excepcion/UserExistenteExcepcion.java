package com.tallerwebi.dominio.excepcion;

public class UserExistenteExcepcion extends RuntimeException{
    public UserExistenteExcepcion(String mensaje){
        super(mensaje);
    }

}
