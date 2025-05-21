package com.tallerwebi.dominio.autenticacion;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tallerwebi.dominio.excepcion.ContraseniaInvalidaExcepcion;
import com.tallerwebi.dominio.excepcion.UserExistenteExcepcion;
import com.tallerwebi.dominio.excepcion.UsuarioInexistenteException;
import com.tallerwebi.presentacion.autenticacion.UsuarioDto;

@Service
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuarioI{
    
    private ArrayList<UsuarioAuth>usuariosRegistradosDB;

    public ServicioUsuarioImpl() {
        this.usuariosRegistradosDB = obtenerUsuariosRegistradosDB();
    }

     
    @Override
    public UsuarioAuth buscarUsuario(String email, String password) {
        for (UsuarioAuth usuario : usuariosRegistradosDB) {
            if(usuario.getEmail().equals(email) && usuario.getPassword().equals(password)){
                return usuario;
            }
        }
        throw new UsuarioInexistenteException();
    }

    @Override
    public void registrarUsuario(UsuarioDto userDto) {
        try{
            buscarUsuarioPorEmail(userDto.getEmail());
            throw new UserExistenteExcepcion("Ya existe un usuario registrado con ese email.");

        }catch(UsuarioInexistenteException exception){
            if(validarContrasenia(userDto.getContrasenia())){
                //el dto lo convierto en una falso modelo de dato
                UsuarioAuth usuario = new UsuarioAuth();
                usuario.setEmail(userDto.getEmail());
                usuario.setPassword(userDto.getContrasenia());
                usuario.setId(this.usuariosRegistradosDB.size()+1);

                this.usuariosRegistradosDB.add(usuario);
            }
        }
    }

    public boolean validarContrasenia(String contrasenia) {
        boolean tieneNumero = tieneNumero(contrasenia);
        boolean tieneMayusc = tieneMayusc(contrasenia);
        boolean tieneMinuscula = tieneMinus(contrasenia);
        boolean tieneCaracterEspecial = tieneEspecial(contrasenia);

        if(tieneNumero && tieneMayusc && tieneMinuscula && tieneCaracterEspecial && contrasenia.length()>=8){
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
    public boolean buscarUsuarioPorEmail(String email) {
        for(UsuarioAuth usuario : usuariosRegistradosDB){
            if(usuario.getEmail().equals(email)){
                return true;
            }
        }
        throw new UsuarioInexistenteException();
    }

    public ArrayList<UsuarioAuth> obtenerUsuariosRegistradosDB(){
        List<UsuarioAuth> usuariosRegistrados = new ArrayList<>();

        usuariosRegistrados.add(new UsuarioAuth(1,"jesi@mail.com", "holaJes1!"));
        usuariosRegistrados.add(new UsuarioAuth(2,"gonza@mail.com", "holaGon1!"));
        usuariosRegistrados.add(new UsuarioAuth(3,"meli@mail.com", "holaMel1!"));
        
        return (ArrayList<UsuarioAuth>) usuariosRegistrados;
    }

}
