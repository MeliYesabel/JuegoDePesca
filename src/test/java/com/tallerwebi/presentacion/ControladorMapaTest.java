package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioMapa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Mockito.mock;

/*POR AHORA: NO NECESITA MOCKITO YA QUE NO TIENE LOGICAS NI DEPENDENCIAS EXTERNAS.
* solo son test que te muestran vistas nada de logica*/
public class ControladorMapaTest {

    /*Controllador Test X es donde debo poner le mockito*/
    private ControladorMapa controladorMapa;

    public ServicioMapa servicioMapa = mock(ServicioMapa.class);

    @BeforeEach
    public void init() {
        controladorMapa = new ControladorMapa(servicioMapa);
    }

    @Test
    public void siElJugadorNoTieneMonedasParaDesbloquearElMarDebeMostarMensajeError(){
        ModelAndView mav = controladorMapa.redireccionDeVistasDependiendoDelUsuario("alias_jugador",0.0);
        thenNoSePuedoHacerElCambioDePagMensajeError(mav,"El Usuario no cuenta con Monedas");

    }

    private void thenNoSePuedoHacerElCambioDePagMensajeError(ModelAndView mav, String mensajeError) {
        assertThat(mav.getViewName(),equalToIgnoringCase("vistaMapa"));
        assertThat(mav.getModel().get("mensajeDeVistaError"),equalTo(mensajeError));

    }


    @Test
    public void alClickearVistaLogrosMeRedirigeAVistaLogros() {
        ModelAndView cm = controladorMapa.irAVistaLogros();
        thenLaVistaFueRedirigidaExitosamente(cm,"vistaLogros");
    }

    @Test
    public void alClickearVistaTiendaMeRedirigeAVistaTienda() {
        ModelAndView cm = controladorMapa.irAVistaTienda();
        thenLaVistaFueRedirigidaExitosamente(cm,"vistaTienda");
    }

    @Test
    public void alClickearUnMapaDesboqueadoPreviamenteVoyAVistaSeleccion() {
        ModelAndView cm = controladorMapa.irAVistaSeleccion();
        thenLaVistaFueRedirigidaExitosamente(cm,"vistaSeleccion");
    }

    private void thenLaVistaFueRedirigidaExitosamente(ModelAndView cm, String vista) {
        assertThat(cm.getViewName(),equalToIgnoringCase(vista));

    }



}
