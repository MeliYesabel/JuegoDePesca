package com.tallerwebi.dominio.repositorio;

import com.tallerwebi.dominio.entidad.TokenRecuperacionContrasenia;

public interface RepositorioTokenRecupContrasenia {
    void eliminar(TokenRecuperacionContrasenia tokenRecibido);

    void guardar(TokenRecuperacionContrasenia tokenEntidad);

    TokenRecuperacionContrasenia buscarPorToken(String token);
}
