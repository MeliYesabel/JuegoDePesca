package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.servicio.ServicioUsuarioAuthI;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class ControladorRecuperacionContraseniaTest {
    private ServicioUsuarioAuthI servicioUsuarioI = mock(ServicioUsuarioAuthI.class);
    private ControladorRecuperoContrasenia controladorRecuperoContrasenia = new ControladorRecuperoContrasenia(servicioUsuarioI);

    @Test
    public void queSePuedaCrearFormRecuperacionContrasenia() {
        givenQueNoExisteFormRecuperoContrasenia();
        ModelAndView mav = whenSeCreaElFormRecuperoContrasenia();
        thenSePuedeRecuperarContrasenia(mav);
    }

    private void givenQueNoExisteFormRecuperoContrasenia() {
    }

    private ModelAndView whenSeCreaElFormRecuperoContrasenia(){
        return controladorRecuperoContrasenia.mostrarFormRecuperoContrasenia();
    }

    private void thenSePuedeRecuperarContrasenia(ModelAndView mav) {
        String vistaEsperada= "recuperar-contrasenia";
        String vistaObtenida= mav.getViewName();

        assertThat(vistaObtenida, equalTo(vistaEsperada));

    }

}
