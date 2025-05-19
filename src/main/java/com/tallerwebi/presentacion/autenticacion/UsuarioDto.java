package com.tallerwebi.presentacion.autenticacion;

public class UsuarioDto {
    private String email;
    private String contrasenia;

    public UsuarioDto() {
    }

    public UsuarioDto(String contrasenia, String email) {
        this.contrasenia = contrasenia;
        this.email = email;
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
}
