package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Objeto;
import com.tallerwebi.dominio.excepcion.MonedasInsuficientesException;
import com.tallerwebi.dominio.repositorio.RepositorioJugador;
import com.tallerwebi.dominio.repositorio.RepositorioObjeto;
import com.tallerwebi.dominio.servicio.ServicioJugador;
import com.tallerwebi.dominio.servicio.ServicioTienda;
import com.tallerwebi.presentacion.dto.UsuarioSesionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorTiendaTest {

    private Jugador jugador;
    private Objeto objeto;
    private ServicioTienda servicioTienda;
    private ServicioJugador servicioJugador;
    private ControladorTienda controladorTienda;
    private  HttpSession session;
    private RepositorioObjeto repositorioObjeto;
    private RepositorioJugador repositorioJugador;
    private UsuarioSesionDto usuarioSesion;
    @BeforeEach
    public void init(){
        repositorioObjeto = mock(RepositorioObjeto.class);
        repositorioJugador = mock(RepositorioJugador.class);
        jugador = new Jugador();
       jugador.setId(1L);
        usuarioSesion = new UsuarioSesionDto(1L,"Gonza","admin");
         objeto = new Objeto(100.0,"caña metal");
         objeto.setIdObjeto(1L);
        servicioTienda = mock(ServicioTienda.class);
        servicioJugador = mock(ServicioJugador.class); //lo agregue porque lo puse en el parametro del controlador poner el mokito en los test
        Long idObjeto = objeto.getIdObjeto();
        controladorTienda = new ControladorTienda(servicioTienda,servicioJugador);
       session = mock(HttpSession.class);
       //when(session.getAttribute("jugador")).thenReturn(jugador);
        when(session.getAttribute("idUsuarioLogueado")).thenReturn(1L);
    }



    /*@Test
    public void dadoQueCuandoComproUnObjetoYMeDevuelveTrueQueMeDevulevaLaVistaObjetoHtml(){

        when(servicioJugador.buscarJugadorPorId(1L)).thenReturn(jugador);
        when(servicioJugador.getRepositorioJugador().buscarJugador(1L)).thenReturn(jugador);
        when(servicioTienda.comprarObjeto(jugador,objeto.getIdObjeto())).thenReturn(true); //para que sirve esta linea si la saco y me corre igual el test?
        ModelAndView modelAndView = controladorTienda.comprarObjeto(session,1L);
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("vistaObjeto.html"));


    }*/

    @Test
    public void dadoQueCuandoComproUnObjetoYMeDevuelveTrueQueMeDevulevaLaVistaObjetoHtml() {
        // Simulo que el jugador está logueado

        when(servicioJugador.buscarJugadorPorId(1L)).thenReturn(jugador);

        // Esto no es obligatorio en este caso porque no se usa el return
        // pero lo dejamos para claridad
        when(servicioTienda.comprarObjeto(jugador, objeto.getIdObjeto())).thenReturn(true);

        ModelAndView modelAndView = controladorTienda.comprarObjeto(session, objeto.getIdObjeto());
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("vistaObjeto.html"));
    }



    @Test
    public void dadoQueCuandoComproUnObjetoYMeDevuelveFalseQueMeDevuelvaLaVistaCompraSinExitoHtml() {
        // Simulo jugador logueado
        when(session.getAttribute("jugador")).thenReturn(jugador);
        when(servicioJugador.buscarJugadorPorId(1L)).thenReturn(jugador);

        // Simulo que ocurre una excepción al intentar comprar
        when(servicioTienda.comprarObjeto(jugador, objeto.getIdObjeto()))
                .thenThrow(new MonedasInsuficientesException("No hay monedas"));

        ModelAndView modelAndView = controladorTienda.comprarObjeto(session, objeto.getIdObjeto());
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("vistaCompraSinExito.html"));
    }

    @Test
    public void dadoQueComproCarnadaExitosamenteQueMeLleveALaVistaCompraCarnadaExitosa(){
        // jugador.setMonedas(30.0);
        when(servicioJugador.buscarJugadorPorId(1L)).thenReturn(jugador);
        when(servicioTienda.comprarCarnada(jugador,3)).thenReturn(true);
        ModelAndView modelAndView = controladorTienda.comprarCarnada(session,3);
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("vistaCompraCarnadaExitosa.html"));
    }

    @Test
    public void dadoQueComproCarnadaSinExitoQueMeLleveALaVistaCompraCarnadaSinExitoso(){
        when(servicioJugador.buscarJugadorPorId(1L)).thenReturn(jugador);
        when(servicioTienda.comprarCarnada(jugador,3)).thenThrow(new MonedasInsuficientesException("No hay monedas"));
        ModelAndView modelAndView = controladorTienda.comprarCarnada(session,3);
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("vistaCompraCarnadaSinExitoso.html"));
    }

}
