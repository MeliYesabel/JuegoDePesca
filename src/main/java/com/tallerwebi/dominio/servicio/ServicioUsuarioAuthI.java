package com.tallerwebi.dominio.servicio;

import com.tallerwebi.dominio.entidad.UsuarioAuth;
import com.tallerwebi.presentacion.dto.UsuarioDto;

public interface ServicioUsuarioAuthI {
    void registrarUsuario(UsuarioDto usuarioDto);

    UsuarioAuth autenticar(String emailIngresado, String contraseniaIngresada);
}