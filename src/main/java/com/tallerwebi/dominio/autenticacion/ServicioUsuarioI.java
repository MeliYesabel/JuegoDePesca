package com.tallerwebi.dominio.autenticacion;

import com.tallerwebi.presentacion.autenticacion.UsuarioDto;

public interface ServicioUsuarioI {
    UsuarioAuth buscarUsuario(String email, String password);
    void registrarUsuario(UsuarioDto user);
    boolean buscarUsuarioPorEmail(String email);

}
