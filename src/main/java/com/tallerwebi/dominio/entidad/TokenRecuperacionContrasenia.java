package com.tallerwebi.dominio.entidad;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TokenRecuperacionContrasenia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")
    private UsuarioAuth usuarioAuth;

    private String token;
    private LocalDateTime expiracionToken;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioAuth getUsuarioAuth() {
        return usuarioAuth;
    }

    public void setUsuarioAuth(UsuarioAuth usuarioAuth) {
        this.usuarioAuth = usuarioAuth;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiracionToken() {
        return expiracionToken;
    }

    public void setExpiracionToken(LocalDateTime expiracionToken) {
        this.expiracionToken = expiracionToken;
    }
}
