package com.tallerwebi.dominio.servicio;

import com.tallerwebi.dominio.entidad.TokenRecuperacionContrasenia;
import com.tallerwebi.dominio.entidad.UsuarioAuth;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioTokenRecupContraseniaImpl implements ServicioTokenRecupContrasenia {
    @Override
    public void enviarTokenRecupacion(UsuarioAuth usuario, HttpServletRequest request) {

    }

    @Override
    public TokenRecuperacionContrasenia buscarPorToken(String token) {
        return null;
    }
}
