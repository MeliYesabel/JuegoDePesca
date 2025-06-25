package com.tallerwebi.dominio.repositorio;

import com.tallerwebi.dominio.entidad.UsuarioAuth;

public interface RepositorioUsuarioAuth {
    UsuarioAuth buscarPorMail(String email);

    void guardar(UsuarioAuth nuevoUsuario);

    void actualizar(UsuarioAuth usuario);
}
