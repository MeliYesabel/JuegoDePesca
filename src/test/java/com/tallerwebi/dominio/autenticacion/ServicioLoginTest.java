package com.tallerwebi.dominio.autenticacion;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;


import org.junit.jupiter.api.Test;

import com.tallerwebi.dominio.excepcion.UsuarioInexistenteException;

public class ServicioLoginTest {
   ServicioUsuarioI servicioUsuarioI = new ServicioUsuarioImpl();

   @Test
   public void dadoEmailYContraseniaElLoginEsExitoso(){
      givenUnUsuarioNoLogueado();
      UsuarioAuth usuarioLogueado = whenLogueoUsuario("jesi@mail.com", "holaJes1!");
      thenElLogueoEsExitoso(usuarioLogueado);
   }

   @Test
   public void dadoUnEmailInexistenteElLoginFalla(){
      givenUnUsuarioNoLogueado();
      assertThrows(UsuarioInexistenteException.class,()-> whenLogueoUsuario("belen@mail.com", "asdf"));
   }

   
   private void thenElLogueoEsExitoso(UsuarioAuth usuarioLogueado) {
      assertThat(usuarioLogueado, is(notNullValue()));
      
   }
   private UsuarioAuth whenLogueoUsuario(String email, String password) {
      UsuarioAuth usuarioBuscado = servicioUsuarioI.buscarUsuario(email, password);
      return usuarioBuscado;
   }

   private ArrayList<UsuarioAuth> givenUnUsuarioNoLogueado() {
      return ((ServicioUsuarioImpl)servicioUsuarioI).obtenerUsuariosRegistradosDB();
   }
    
}

