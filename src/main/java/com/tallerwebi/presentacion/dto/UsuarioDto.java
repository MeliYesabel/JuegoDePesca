package com.tallerwebi.presentacion.dto;

public class UsuarioDto {
    private String email;
    private String contrasenia;
    private String contraseniaRepetida;

    public UsuarioDto() {
    }


    public UsuarioDto(String email, String contrasenia) {
        this.email = email;
        this.contrasenia = contrasenia;
    }


    public UsuarioDto(String email,String contrasenia, String contraseniaRepetida) {
        this.contrasenia = contrasenia;
        this.email = email;
        this.contraseniaRepetida = contraseniaRepetida;
    }

    public UsuarioDto(String mail) {
        this.email = mail;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getContraseniaRepetida() {
        return contraseniaRepetida;
    }

    public void setContraseniaRepetida(String contraseniaRepetida) {
        this.contraseniaRepetida = contraseniaRepetida;
    }

}
