package com.tallerwebi.dominio.servicio;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.entidad.TokenRecuperacionContrasenia;
import com.tallerwebi.dominio.entidad.UsuarioAuth;
import com.tallerwebi.dominio.excepcion.ContraseniaInvalidaExcepcion;
import com.tallerwebi.dominio.excepcion.UsuarioExistenteExcepcion;
import com.tallerwebi.dominio.excepcion.UsuarioInexistenteException;
import com.tallerwebi.dominio.repositorio.RepositorioTokenRecupContrasenia;
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
    private RepositorioTokenRecupContrasenia tokenRecup;

    @Autowired
    public ServicioUsuarioAuthImpl(RepositorioUsuarioAuth repositorioUsuarioAuth) {
        this.repositorioUsuarioAuth = repositorioUsuarioAuth;
    }

    @Override
    public void actualizarContrasenia(UsuarioDto usuarioDto, TokenRecuperacionContrasenia tokenRecibido) {
        String password = usuarioDto.getContrasenia();
        UsuarioAuth usuario = tokenRecibido.getUsuarioAuth();

        if(validarContrasenia(password)){
            String passwordHasheada = PasswordUtil.hashear(password);
            usuario.setPassword(passwordHasheada);
            repositorioUsuarioAuth.actualizar(usuario);
            tokenRecup.eliminar(tokenRecibido);
        }


    }

    @Override
    public void registrarUsuario(UsuarioDto usuarioDto) {
        String email = usuarioDto.getEmail();
        String password = usuarioDto.getContrasenia();
        String username = email.split("@")[0];

        UsuarioAuth encontrado = repositorioUsuarioAuth.buscarPorMail(email);

        if(encontrado!=null){
            throw new UsuarioExistenteExcepcion("Ya existe un usuario registrado con ese email");
        }
        if(validarContrasenia(password)){
            Jugador nuevoUsuario = new Jugador();
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setUsername(username);

            String contraseniaHasheada = PasswordUtil.hashear(password);
            nuevoUsuario.setPassword(contraseniaHasheada);

            repositorioUsuarioAuth.guardar(nuevoUsuario);

        }

    }

    @Override
    public UsuarioAuth autenticar(String emailIngresado, String contraseniaIngresada) {
        UsuarioAuth usuarioAuth = repositorioUsuarioAuth.buscarPorMail(emailIngresado);
        if(usuarioAuth!=null && PasswordUtil.verificar(contraseniaIngresada, usuarioAuth.getPassword())){
            return usuarioAuth;
        }
        throw new UsuarioInexistenteException();
    }

    @Override
    public UsuarioAuth buscarUsuarioPorMail(String mail) {
        UsuarioAuth encontrado = repositorioUsuarioAuth.buscarPorMail(mail);
        if(encontrado!=null)
            return  encontrado;
        throw new UsuarioInexistenteException();
    }

    private boolean validarContrasenia(String password) {
        boolean tieneNumero = tieneNumero(password);
        boolean tieneMayusc = tieneMayusc(password);
        boolean tieneMinuscula = tieneMinus(password);
        boolean tieneCaracterEspecial = tieneEspecial(password);

        if(tieneNumero && tieneMayusc && tieneMinuscula && tieneCaracterEspecial && password.length()>=8){
            return true;
        };

        throw new ContraseniaInvalidaExcepcion();
    }

    private boolean tieneNumero(String pswd){
        return (pswd.contains("0") ||pswd.contains("1") ||pswd.contains("2") ||pswd.contains("3") ||
                pswd.contains("4") ||pswd.contains("5") ||pswd.contains("6")||pswd.contains("7") ||
                pswd.contains("8") ||pswd.contains("9"));
    }

    private boolean tieneMayusc(String pswd){
        char letraInicio = 'A', letraFin = 'Z';
        int largoPWD = pswd.length();

        return contieneTalLetra(pswd, letraInicio, letraFin, largoPWD);
    }

    private boolean tieneMinus(String pswd){
        char letraInicio = 'a', letraFin = 'z';
        int largoPWD = pswd.length();

        return contieneTalLetra(pswd, letraInicio, letraFin, largoPWD);
    }

    private boolean contieneTalLetra(String pswd, char letraInicio, char letraFin, int largoPWD) {
        for(int i=0; i<largoPWD; i++) {
            for(int j=(int)letraInicio; j<(int)letraFin ; j++) {
                if(pswd.charAt(i)== (char)j) return true;
            }
        }
        return false;
    }


    private boolean tieneEspecial(String pswd){
        return (pswd.contains("!") ||pswd.contains("@") ||pswd.contains("$") ||pswd.contains("&"));
    }
}
