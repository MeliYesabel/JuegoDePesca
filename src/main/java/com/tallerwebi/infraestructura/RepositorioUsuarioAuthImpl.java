package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.UsuarioAuth;
import com.tallerwebi.dominio.repositorio.RepositorioUsuarioAuth;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioUsuarioAuthImpl implements RepositorioUsuarioAuth {
    @Override
    public UsuarioAuth buscarPorMail(String email) {
        return null;
    }

    @Override
    public void guardar(UsuarioAuth nuevoUsuario) {

    }
}
