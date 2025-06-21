package com.tallerwebi.presentacion.dto;

public class UsuarioSesionDto {
    private Long id;
    private String username;
    private String rol;

    public UsuarioSesionDto(Long id, String username, String rol) {
        this.id = id;
        this.username = username;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public String getRol() {
        return rol;
    }

    public String getUsername() {
        return username;
    }
}
