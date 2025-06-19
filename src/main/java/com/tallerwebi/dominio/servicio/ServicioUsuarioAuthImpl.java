package com.tallerwebi.dominio.servicio;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.entidad.UsuarioAuth;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import com.tallerwebi.dominio.excepcion.UsuarioExistenteExcepcion;
import com.tallerwebi.dominio.repositorio.RepositorioUsuarioAuth;
import com.tallerwebi.dominio.utils.PasswordUtil;
import com.tallerwebi.presentacion.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServicioUsuarioAuthImpl implements ServicioUsuarioAuthI {
    private RepositorioUsuarioAuth repositorioUsuarioAuth;

    @Autowired
    public ServicioUsuarioAuthImpl(RepositorioUsuarioAuth repositorioUsuarioAuth) {
        this.repositorioUsuarioAuth = repositorioUsuarioAuth;
    }

    @Override
    public void registrarUsuario(UsuarioDto usuarioDto) {
        String email = usuarioDto.getEmail();
        String password = usuarioDto.getContrasenia();

        UsuarioAuth encontrado = repositorioUsuarioAuth.buscarPorMail(email);

        if(encontrado!=null){
            throw new UsuarioExistenteExcepcion("Ya existe un usuario registrado con ese email");
        }
        if(validarContrasenia(password)){
            UsuarioAuth nuevoUsuario = new Jugador();
            nuevoUsuario.setEmail(email);

            String contraseniaHasheada = PasswordUtil.hashear(password);
            nuevoUsuario.setPassword(contraseniaHasheada);

            repositorioUsuarioAuth.guardar(nuevoUsuario);

        }

    }

    private boolean validarContrasenia(String password) {
        return false;
    }
}
