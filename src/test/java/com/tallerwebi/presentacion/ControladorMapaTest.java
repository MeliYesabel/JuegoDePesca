package com.tallerwebi.presentacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

/*POR AHORA: NO NECESITA MOCKITO YA QUE NO TIENE LOGICAS NI DEPENDENCIAS EXTERNAS.
* solo son test que te muestran vistas nada de logica*/
public class ControladorMapaTest {

    private ControladorMapa controladorMapa;

    @BeforeEach
    public void init() {
        controladorMapa = new ControladorMapa();
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
