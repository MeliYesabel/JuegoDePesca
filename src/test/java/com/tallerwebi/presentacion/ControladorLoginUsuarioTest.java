package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.servicio.ServicioUsuarioI;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ControladorLoginUsuarioTest {
    //mockito
    private ServicioUsuarioI servicioUsuarioI = mock(ServicioUsuarioI.class);
    private ControladorLogin controladorLogin = new ControladorLogin(servicioUsuarioI);

    @Test
    public void queSeGenereFormularioLoginParaQueSePuedaLoguearUsuario() {
        givenQueNoExisteFormularioLogIn();
        ModelAndView mav = whenSeCreaElFormularioLogIn();
        thenSePuedeLoguearUsuario(mav);
    }

    private void givenQueNoExisteFormularioLogIn() {
    }

    private ModelAndView whenSeCreaElFormularioLogIn() {
        return controladorLogin.mostrarLogin();
    }

    private void thenSePuedeLoguearUsuario(ModelAndView mav) {
        String vistaEsperada = "login-usuario";
        String vistaObtenida = mav.getViewName();
        assertThat(vistaObtenida, equalTo(vistaEsperada));
        assertTrue(mav.getModel().containsKey("usuarioDto"));
    }
}
