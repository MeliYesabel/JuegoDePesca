package com.tallerwebi.presentacion.autenticacion;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControladorRecuperacionContraseniaTest {

    private ControladorRecuperoContrasenia controladorRecuperoContrasenia = new ControladorRecuperoContrasenia();

    @Test
    public void queSePuedaCrearFormRecuperacionContrasenia() {
        givenQueNoExisteFormRecuperoContrasenia();
        ModelAndView mav = whenSeCreaElFormRecuperoContrasenia();
        thenSePuedeRecuperarContrasenia(mav);
    }

    private void thenSePuedeRecuperarContrasenia(ModelAndView mav) {
        String vistaEsperada = "recuperar-contrasenia";
        String vistaGenerada = mav.getViewName();

        assertThat(vistaGenerada, equalTo(vistaEsperada));
        assertTrue(mav.getModel().containsKey("usuarioDto"));
    }

    private ModelAndView whenSeCreaElFormRecuperoContrasenia() {
        return controladorRecuperoContrasenia.mostrarFormRecuperoContrasenia();
    }

    private void givenQueNoExisteFormRecuperoContrasenia() {}
}
