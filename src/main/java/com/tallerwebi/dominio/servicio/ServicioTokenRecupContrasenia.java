package com.tallerwebi.dominio.servicio;

import com.tallerwebi.dominio.entidad.TokenRecuperacionContrasenia;
import com.tallerwebi.dominio.entidad.UsuarioAuth;

import javax.servlet.http.HttpServletRequest;

public interface ServicioTokenRecupContrasenia {
    void enviarTokenRecuperacion(UsuarioAuth usuario, HttpServletRequest request);

    TokenRecuperacionContrasenia buscarPorToken(String token);
}
