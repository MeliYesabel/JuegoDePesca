package com.tallerwebi.dominio.entidad;

public abstract class UsuarioAuth {
    private Integer id;
    private String username;
    private String mail;
    private String contrasenia;
    private String rol;
    private boolean activo;

    public UsuarioAuth() {
    }

    public UsuarioAuth(Integer id, String username, String mail, String contrasenia, String rol, boolean activo) {
        this.id = id;
        this.username = username;
        this.mail = mail;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
