package com.tallerwebi.dominio.autenticacion;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.tallerwebi.dominio.excepcion.UsuarioInexistenteException;

public class ServicioRecuperoContraseniaTest {
    ServicioUsuarioI servicioUsuarioI = new ServicioUsuarioImpl();

    @Test
    public void dadoEmailRegistradoLaRecuperacionDePasswordEsExitosa(){
        givenQueNoHayRecuperacionContrasenia();
        Boolean estadoRecuperacionObtenida = whenRecuperoLaContrasenia("jesi@mail.com");
        thenLaRecuperacionEsExitosa(estadoRecuperacionObtenida);

    }

    private void thenLaRecuperacionEsExitosa(Boolean estadoRecuperacionObtenida) {
        assertTrue(estadoRecuperacionObtenida);
    }

    private Boolean whenRecuperoLaContrasenia(String emailRegistrado) {
        return servicioUsuarioI.buscarUsuarioPorEmail(emailRegistrado);
    }
 
    @Test
    public void dadoUnEmailDeUnUsuarioNoRegistradoLaRecuDePasswordFalla(){
      givenQueNoHayRecuperacionContrasenia();
      assertThrows(UsuarioInexistenteException.class,()-> whenRecuperoLaContrasenia("belen@mail.com"));
    }
   


    private void givenQueNoHayRecuperacionContrasenia() {}
}
