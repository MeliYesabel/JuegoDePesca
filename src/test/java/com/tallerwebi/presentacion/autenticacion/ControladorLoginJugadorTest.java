package com.tallerwebi.presentacion.autenticacion;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControladorLoginJugadorTest {
    private ControladorLogin controladorLogin = new ControladorLogin();


    @Test
    public void queSeGenereFormularioLoginParaQueSePuedaLoguearUsuario() {
        givenQueNoExisteFormularioLogIn();
        ModelAndView mav = whenSeCreaElFormularioLogIn();
        thenSePuedeLoguearUsuario(mav);
    }

    private void thenSePuedeLoguearUsuario(ModelAndView mav) {
        String vistaEsperada = "login-pescador";
        String vistaObtenida = mav.getViewName();
        assertThat(vistaObtenida, equalTo(vistaEsperada));
        assertTrue(mav.getModel().containsKey("usuarioDto"));
    }

    private ModelAndView whenSeCreaElFormularioLogIn() {
        return controladorLogin.mostrarLogIn();
    }

    private void givenQueNoExisteFormularioLogIn() {
    }
}
