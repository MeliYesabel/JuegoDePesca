package com.tallerwebi.dominio.autenticacion;



public interface ServicioUsuarioI {
    UsuarioAuth buscarUsuario(String email, String password);
    void registrarUsuario(UsuarioAuth user);

}
