package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Mar;
import com.tallerwebi.dominio.excepcion.NoSePuedodesbloquearElMarException;
import com.tallerwebi.dominio.servicio.ServicioMapa;
import com.tallerwebi.dominio.servicio.ServicioJugador;
import com.tallerwebi.presentacion.dto.UsuarioSesionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

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
    public void siAlDesbloquearUnMarLaEjecucionFallaMeRedirijaAVistaMarBloqueado() {
        HttpSession session = mock(HttpSession.class);

        // session
        UsuarioSesionDto usuarioSesion = new UsuarioSesionDto(3L, "Anahi", "PESCADOR");
        Jugador jugador = new Jugador("Anahi", "anis", 300.0, 1);
        jugador.setId(3L);

        Mar mar = new Mar("Mitologia Nordica", 150.0, "mar dos", false);

        when(session.getAttribute("usuarioLogueado")).thenReturn(usuarioSesion);
        when(servicioJugador.buscarJugadorPorId(3L)).thenReturn(jugador);
        when(servicioMapa.obtenerUnMarPorNombre("Mitologia Nordica")).thenReturn(mar);
        when(servicioMapa.obtenerElEstadoDelMarSegunELJugador(mar, jugador)).thenReturn(false); // para que entre al try

        doThrow(new NoSePuedodesbloquearElMarException("Fallo")).when(servicioMapa)
                .desbloquearMarSegunElJugador(mar, jugador);

        ModelAndView mv = controladorMapa.desbloquearMarSeleccionado(session, mar.getNombre());

        assertThat(mv.getViewName(), equalToIgnoringCase("vistaMarBloqueado"));
    }


    @Test
    public void siAlDesbloquearUnMarLaEjecucionEsExitosaSeRedirijaAVistaMapaParaDespuesPoderSeleccionarlo(){
        HttpSession session = mock(HttpSession.class);
        // session
        UsuarioSesionDto usuarioSesion = new UsuarioSesionDto(3L,"Anahi","PERSCADOR");
        Jugador jugador = new Jugador("Anahi","anis",300.0,1);
        jugador.setId(3L);

        Mar mar = new Mar("Mitologia Nordica", 150.0, "mar dos", false);

        when(session.getAttribute("usuarioLogueado")).thenReturn(usuarioSesion);
        when(servicioJugador.buscarJugadorPorId(3L)).thenReturn(jugador);
        when(servicioMapa.obtenerUnMarPorNombre("Mitologia")).thenReturn(mar);
        when(servicioMapa.desbloquearMarSegunElJugador(mar,jugador)).thenReturn(true);

        ModelAndView mv = controladorMapa.desbloquearMarSeleccionado(session,mar.getNombre());
        assertThat(mv.getViewName(), equalToIgnoringCase("redirect:/mapa"));

    }

    @Test
    public void siAlBuscarUnMarPorNombreEsInexistenteMeRedirijaAVistaMapaYMeMuestreMarError() {
        HttpSession session = mock(HttpSession.class);

        // session
        UsuarioSesionDto usuarioSesion = new UsuarioSesionDto(3L,"Anahi","PERSCADOR");
        Jugador jugador = new Jugador("Anahi","anis",30.0,1);
        jugador.setId(3L);

        Mar mar = new Mar("Mitologia Nordica", 150.0, "mar dos", false);


        when(session.getAttribute("usuarioLogueado")).thenReturn(usuarioSesion);
        when(servicioJugador.buscarJugadorPorId(3L)).thenReturn(jugador);
        when(servicioMapa.obtenerUnMarPorNombre("Mitologia")).thenReturn(mar);

        ModelAndView mv = whenLaRedireccionEsSegunElJugadorOMar(session,mar.getNombre());
        thenLaVistaFueRedirigidaADondeIba(mv, "redirect:/mapa");
    }

    @Test
    public void queAlObtenerTodaLaListaDeMaresIrAVistaMapa(){
        List<Mar> listaMar = givenInstanciaDeTodosLosMares();
        //mock
        when(servicioMapa.obtenerTodaListaDeMares()).thenReturn(listaMar);
        //when
        ModelAndView mv = controladorMapa.irAVistaMapa();

        thenLaVistaFueRedirigidaADondeIba(mv,"vistaMapa");
    }

    private List<Mar> givenInstanciaDeTodosLosMares() {
        List<Mar> listaMar = Arrays.asList(
                new Mar("Mitologia griega", 0.0, "mar uno", false),
                new Mar("Mitologia Nordica", 150.0, "mar dos", true),
                new Mar("Mitologia Japonesa", 200.0, "mar tres", true),
                new Mar("Mitología Yoruba", 250.0, "mar cuatro", true),
                new Mar("Mitología Indú", 300.0, "mar cinco", true),
                new Mar("Mitología Asteca", 350.0, "mar seis", true),
                new Mar("Mitología China", 450.0, "mar siete", true)

        );
        return listaMar;
    }

    @Test
    public void siElMarDeUnCiertojugadorEstaDESBloqueadoIrAVistaSeleccion(){
        HttpSession session = mock(HttpSession.class);

        // session
        UsuarioSesionDto usuarioSesion = new UsuarioSesionDto(3L,"Anahi","PERSCADOR");
        Jugador jugador = new Jugador("Anahi","anis",30.0,1);
        jugador.setId(3L);

        Mar mar = new Mar("Mitologia Nordica", 150.0, "mar dos", false);


        when(session.getAttribute("usuarioLogueado")).thenReturn(usuarioSesion);
        when(servicioJugador.buscarJugadorPorId(3L)).thenReturn(jugador);
        when(servicioMapa.obtenerUnMarPorNombre("Mitologia Nordica")).thenReturn(mar);
        when(servicioMapa.obtenerElEstadoDelMarSegunELJugador(mar,jugador)).thenReturn(false);

        ModelAndView cm = whenLaRedireccionEsSegunElJugadorOMar(session,mar.getNombre());

        assertThat(cm.getViewName(),equalToIgnoringCase("vistaSeleccion"));
    }

    @Test
    public void siElMarDeUnCiertojugadorEstaBloqueadoIrAVistaMarBloqueado() {
        HttpSession session = mock(HttpSession.class);

        // session
        UsuarioSesionDto usuarioSesion = new UsuarioSesionDto(3L,"Anahi","PERSCADOR");
        Jugador jugador = new Jugador("Anahi","anis",30.0,1);
        jugador.setId(3L);

        Mar mar = new Mar("Mitologia Nordica", 150.0, "mar dos", true);


        when(session.getAttribute("usuarioLogueado")).thenReturn(usuarioSesion);
        when(servicioJugador.buscarJugadorPorId(3L)).thenReturn(jugador);
        when(servicioMapa.obtenerUnMarPorNombre("Mitologia Nordica")).thenReturn(mar);
        when(servicioMapa.obtenerElEstadoDelMarSegunELJugador(mar,jugador)).thenReturn(true);

        ModelAndView cm = whenLaRedireccionEsSegunElJugadorOMar(session,mar.getNombre());

        assertThat(cm.getViewName(),equalToIgnoringCase("vistaMarBloqueado"));

    }

    @Disabled
    @Test
    public void siExisteElJugadorRedirijaAVistaMapa(){
        HttpSession session = mock(HttpSession.class);
        UsuarioSesionDto usuarioSesion = new UsuarioSesionDto(3L,"Anahi","PERSCADOR");
        Jugador jugador = new Jugador("Anahi","anis",30.0,1);
        jugador.setId(3L);
        Mar mar = new Mar();

        when(session.getAttribute("usuarioLogueado")).thenReturn(usuarioSesion);
        when(servicioJugador.buscarJugadorPorId(3L)).thenReturn(jugador);

        ModelAndView cm = whenLaRedireccionEsSegunElJugadorOMar(session,mar.getNombre());
        thenLaVistaFueRedirigidaADondeIba(cm,"redirect:/mapa");

    }

    private ModelAndView whenLaRedireccionEsSegunElJugadorOMar(HttpSession session, String mar) {
        ModelAndView mv = controladorMapa.redireccionSegunSiEstaBloqueadoONo(session, mar);
        return mv;
    }

    @Test
    public void alClickearVistaLogrosMeRedirigeAVistaLogros() {
        ModelAndView cm = controladorMapa.irAVistaLogros();
        thenLaVistaFueRedirigidaADondeIba(cm,"vistaLogros");
    }


    private void thenLaVistaFueRedirigidaADondeIba(ModelAndView cm, String vista) {
        assertThat(cm.getViewName(),equalToIgnoringCase(vista));

    }



}
