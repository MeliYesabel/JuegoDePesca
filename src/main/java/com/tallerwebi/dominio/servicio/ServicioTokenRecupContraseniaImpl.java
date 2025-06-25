package com.tallerwebi.dominio.servicio;

import com.tallerwebi.dominio.entidad.TokenRecuperacionContrasenia;
import com.tallerwebi.dominio.entidad.UsuarioAuth;
import com.tallerwebi.dominio.repositorio.RepositorioTokenRecupContrasenia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class ServicioTokenRecupContraseniaImpl implements ServicioTokenRecupContrasenia {
    private RepositorioTokenRecupContrasenia repoTokenRecupPswd;
    private ServicioMail servicioMail;

    @Autowired
    public ServicioTokenRecupContraseniaImpl(RepositorioTokenRecupContrasenia repoTokenRecupPswd, ServicioMail servicioMail) {
        this.repoTokenRecupPswd = repoTokenRecupPswd;
        this.servicioMail = servicioMail;
    }

    @Override
    public void enviarTokenRecuperacion(UsuarioAuth usuario, HttpServletRequest request) {
        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new RuntimeException("El usuario no tiene un correo electrónico válido");
        }
        //Genero el token random
        String tokenRandom = UUID.randomUUID().toString();

        //Crear un objeto con expiracion de 30 min adelante
        TokenRecuperacionContrasenia tokenEntidad = new TokenRecuperacionContrasenia();
        tokenEntidad.setToken(tokenRandom);
        tokenEntidad.setUsuarioAuth(usuario);
        tokenEntidad.setExpiracionToken(LocalDateTime.now().plusMinutes(30));

        //Regitro el token
        repoTokenRecupPswd.guardar(tokenEntidad);

        //construir la url para recuperecion
        String url = request.getScheme() + "://" +
                    request.getServerName() + ":" +
                    request.getServerPort() +
                    request.getContextPath() + "/cambiar-contrasenia?token="+tokenRandom;

        //Enviar correo
        String asunto = "Recuperacion de contrasenia";
        String cuerpo = "Hola "+ usuario.getUsername() +
                ". Para cambiar tu contrasenia, hace click en el siguiente enlace: \n " +
                url +"\n\n"+ "Este enlace expirara en 30 min.";
        System.out.println("DEBUG >> Email del usuario en servicio: " + usuario.getEmail());

        servicioMail.enviarEmail(usuario.getEmail(), asunto, cuerpo);

    }

    @Override
    public TokenRecuperacionContrasenia buscarPorToken(String token) {
        return repoTokenRecupPswd.buscarPorToken(token);
    }
}
