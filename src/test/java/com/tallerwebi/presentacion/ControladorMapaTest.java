package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Mar;
import com.tallerwebi.dominio.MonedasInsuficientesException;
import com.tallerwebi.dominio.ServicioMapa;
import org.dom4j.rule.Mode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Mockito.*;

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
    /*segundo sprint
    * PREGUNTa : es redundante hacer un test dond eme lanze un aexcepcion y otro test me testee las redirecciones */

    /*31/05 agregue*/
    @Test
    public void fijarseSiElJugadorTieneElMarDESBLOQUEADOExito() {
        ModelAndView mv = controladorMapa.redireccionDeVistasDependiendoDelUsuario("alias_jugador",4.0);
        thenLaVistaFueRedirigidaExitosamente(mv,"vistaSeleccion");
    }

    @Test
    public void fijarseSiElJugadorTieneElMarBloqueadoLanzarUnError(){
        ModelAndView mv = controladorMapa.redireccionDeVistasDependiendoDelUsuario("alias_jugador",4.0);
        thenNoSePuedoHacerElCambioDePagMensajeError(mv,"mensajeDeVistaError","El mar se encuentra en estado BLOQUEADO");
    }

    /*servicio(agregue logica) + exception*/
    @Test
    public void siElJugadorNOTieneSuficientesMonedasParaDesbloquearElMarDebeLanzarMonedasInsuficientesException() {
        /*test con exception mas mock xq usa logica del test servicio */
        doThrow(MonedasInsuficientesException.class).when(servicioMapa).calcularSiSePuedeDesbloquearUnMar("alias_jugador",90.0);
        ModelAndView mav = controladorMapa.redireccionDeVistasDependiendoDelUsuario("alias_jugador",90.0);

        thenNoSePuedoHacerElCambioDePagMensajeError(mav,"mensajeErrorMonedas","El Usuario no tiene suficientes monedas para desbloquear el mar");
    }

    @Test
    public void siElJugadorTieneSuficientesMonedasParaDesbloquearElMarDebeCambiardePaginaAVistaSeleccion() {
        ModelAndView mav = controladorMapa.redireccionDeVistasDependiendoDelUsuario("alias_jugador",150.0);
        thenLaVistaFueRedirigidaExitosamente(mav,"vistaSeleccion");
    }

   /* @Test -> este test no me va a dar por que en ningun lado estoy usando el EXCEPCION algo que antes si
    public void siElJugadorNOTieneSuficientesMonedasParaDesbloquearElMarDebeCambiardePaginaAVistaMapa() {
        ModelAndView mav = controladorMapa.redireccionDeVistasDependiendoDelUsuario("alias_jugador",80.0);
        thenNoSePuedoHacerElCambioDePagMensajeError(mav,"mensajeErrorMonedas","El Usuario no tiene suficientes monedas para desbloquear el mar");
    }*/

    @Test
    public void siElJugadorTieneMonedasSuficientesParaDesbloquearElMarDebeCambiarDePaginaAVistaSeleccion() {
        ModelAndView mav = controladorMapa.redireccionDeVistasDependiendoDelUsuario("alias_jugador",100.0);
        thenLaVistaFueRedirigidaExitosamente(mav,"vistaSeleccion");
    }

    @Test
    public void siElJugadorNoTieneMonedasParaDesbloquearElMarDebeMostarMensajeError(){
        ModelAndView mav = controladorMapa.redireccionDeVistasDependiendoDelUsuario("alias_jugador",0.0);
        thenNoSePuedoHacerElCambioDePagMensajeError(mav,"mensajeDeVistaError","El Usuario no cuenta con Monedas");

    }

    private void thenNoSePuedoHacerElCambioDePagMensajeError(ModelAndView mav, String claveMensaje,String valorMensaje) {
        assertThat(mav.getViewName(),equalToIgnoringCase("vistaMapa"));
        assertThat(mav.getModel().get(claveMensaje),equalTo(valorMensaje));

    }

/*primer sprint*/
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
