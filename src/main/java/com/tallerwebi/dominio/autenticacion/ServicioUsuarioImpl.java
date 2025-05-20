package com.tallerwebi.dominio.autenticacion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tallerwebi.dominio.excepcion.UsuarioInexistenteException;

@Service
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuarioI{

    private ArrayList<UsuarioAuth>usuariosRegistradosDB = obtenerUsuariosRegistradosDB();
     
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
    public void registrarUsuario(UsuarioAuth user) {
        UsuarioAuth usuarioExistente = buscarUsuarioPorEmail(user.getEmail());
        if(usuarioExistente==null){

        }
    
    }

    private UsuarioAuth buscarUsuarioPorEmail(String email) {
        return null;
    }

    public ArrayList<UsuarioAuth> obtenerUsuariosRegistradosDB(){
        List<UsuarioAuth> usuariosRegistrados = new ArrayList<>();

        usuariosRegistrados.add(new UsuarioAuth(1,"jesi@mail.com", "holaJes1!"));
        usuariosRegistrados.add(new UsuarioAuth(2,"gonza@mail.com", "holaGon1!"));
        usuariosRegistrados.add(new UsuarioAuth(3,"meli@mail.com", "holaMel1!"));
        
        return (ArrayList<UsuarioAuth>) usuariosRegistrados;
    }

}
