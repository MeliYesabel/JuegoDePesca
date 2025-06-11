package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.ServicioSeleccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Mockito.mock;

public class ControladorSeleccionTest {

    private ControladorSeleccion controladorSeleccion;
    public Jugador jugador;
    public ServicioSeleccion servicioSeleccion = mock(ServicioSeleccion.class);

    @BeforeEach
    public void init() {
        controladorSeleccion = new ControladorSeleccion(servicioSeleccion);
    }

    /*comprobar este test xq en el controller como esta setteando debo buscar la manera para que despues vualva as er original
    @Test
    public void siElJugadorTieneCarnadasMasDeCeroYMenorACincoIrAVistaRun() {
        ModelAndView mv = controladorSeleccion.validacionesDelJuegoAntesDeCambiarDeVistaARun(jugador);
        thenRedireccionExitosa(mv,"vistaRun");
    }*/

    private void thenRedireccionExitosa(ModelAndView mv, String vistaRun) {
        assertThat(mv.getViewName(),equalToIgnoringCase("vistaRun"));
    }

    @Test
    public void siElJuugadorNoTieneNadaDeCarnadaIrAVistaSeleccion() {
        ModelAndView mv = controladorSeleccion.validacionesDelJuegoAntesDeCambiarDeVistaARun(jugador);
        thenMensajeError(mv,"errorCantCarnada","El jugador no tiene carnadas");
    }

    @Test
    public void siElJugadorTieneLaCantidadDeCarnadaMayorACincoIrVistaSeleccion() {
       // jugador.setCant_carnada(6);
        ModelAndView mv = controladorSeleccion.validacionesDelJuegoAntesDeCambiarDeVistaARun(jugador);
        thenMensajeError(mv,"errorCantCarnada","El jugador no puede tener mas de cinco carnada");
    }

    private void thenMensajeError(ModelAndView mv, String claveMensajeError, String valorMnesajeError) {
        assertThat(mv.getModel().get(claveMensajeError),equalTo(valorMnesajeError));
        assertThat(mv.getViewName(),equalToIgnoringCase("vistaSeleccion"));
    }

}
