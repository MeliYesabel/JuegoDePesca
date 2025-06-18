package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.Mar;
import com.tallerwebi.dominio.ServicioMapa;
import com.tallerwebi.dominio.ServicioJugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Mockito.*;

/*Los Test Controladores solo sirven para probar las Redirecciones de paginas */

public class ControladorMapaTest {

    private ControladorMapa controladorMapa; /*paso el objeto q prueba en este caso el controlador mapa*/

    public ServicioMapa servicioMapa = mock(ServicioMapa.class);
    public ServicioJugador servicioJugador = mock(ServicioJugador.class);

    @BeforeEach
    public void init() {
        controladorMapa = new ControladorMapa(servicioMapa, servicioJugador);
    }

    @Test
    public void siExisteElJugadorRedirijaAVistaMapa(){
        Jugador jugador = new Jugador("Anahi","anis",30.0,1);
        jugador.setId_jugador(3L);
        Mar mar = new Mar();

        when(servicioJugador.buscarJugadorPorId(3L)).thenReturn(jugador);

        ModelAndView cm = controladorMapa.redireccionSegunSiEstaBloqueadoONo(jugador, mar);
        thenLaVistaFueRedirigidaADondeIba(cm,"vistaSeleccion");

    }

    @Test
    public void siElJugadorEsNullQueRedirijaAVistaLogin(){
        Jugador jugador = new Jugador();
        jugador.setId_jugador(1L);

        Mar mar = new Mar();
        when(servicioJugador.buscarJugadorPorId(1L)).thenReturn(null);

        ModelAndView cm = controladorMapa.redireccionSegunSiEstaBloqueadoONo(jugador, mar);
        thenLaVistaFueRedirigidaADondeIba(cm, "login");
    }


    /*segundo sprint
    * PREGUNTa : es redundante hacer un test dond eme lanze un aexcepcion y otro test me testee las redirecciones */



    /*31/05 agregue


    @Test
    public void fijarseSiElJugadorTieneEstadoDelElMarDESBLOQUEADOExito() {
        ModelAndView mv = controladorMapa.redireccionDeVistasDependiendoDelUsuario("alias_jugador",4.0);
        thenLaVistaFueRedirigidaExitosamente(mv,"vistaSeleccion");
    }

    @Test
    public void fijarseSiElJugadorTieneElEstadoDelMarBloqueadoLanzarUnError(){
        ModelAndView mv = controladorMapa.redireccionDeVistasDependiendoDelUsuario("alias_jugador",4.0);
        thenNoSePuedoHacerElCambioDePagMensajeError(mv,"mensajeDeVistaError","El mar se encuentra en estado BLOQUEADO");
    }*/




    // primer sprint ----------------------------------------------------------------------------
    @Test
    public void alClickearVistaLogrosMeRedirigeAVistaLogros() {
        ModelAndView cm = controladorMapa.irAVistaLogros();
        thenLaVistaFueRedirigidaADondeIba(cm,"vistaLogros");
    }

    @Test
    public void alClickearVistaTiendaMeRedirigeAVistaTienda() {
        ModelAndView cm = controladorMapa.irAVistaTienda();
        thenLaVistaFueRedirigidaADondeIba(cm,"vistaTienda");
    }

    private void thenLaVistaFueRedirigidaADondeIba(ModelAndView cm, String vista) {
        assertThat(cm.getViewName(),equalToIgnoringCase(vista));

    }



}
