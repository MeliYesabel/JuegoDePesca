package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Objeto;
import com.tallerwebi.dominio.servicio.ServicioJugadorImpl;
import com.tallerwebi.infraestructura.RepositorioJugadorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorPersonajeTest {
private Jugador jugador;
private Objeto objeto;
private ServicioJugadorImpl servicioJugador;
private ControladorPersonaje controladorPersonaje;
private HttpSession session;
private RepositorioJugadorImpl repositorioJugador;

@BeforeEach
public void setUp() {
    jugador = new Jugador();
    objeto = new Objeto();
    servicioJugador = mock(ServicioJugadorImpl.class);
    repositorioJugador = mock(RepositorioJugadorImpl.class);
    controladorPersonaje = new ControladorPersonaje(servicioJugador,repositorioJugador);
    session = mock(HttpSession.class);

}

@Test
    public void dadoQueEstoyEnLaVistaPersonajeQueCuandoToqueElBotonCaniaMeDuvuelvaLaVistaObjetosDelJugador() {

    jugador.setId(1L);
    objeto.setIdObjeto(1L);
    jugador.agregarObjeto(objeto);

    when(session.getAttribute("jugador")).thenReturn(jugador);
    when(repositorioJugador.buscarJugador(1L)).thenReturn(jugador);
    when(servicioJugador.equipaCaniaAPersonaje(jugador,objeto.getIdObjeto())).thenReturn(true);
    ModelAndView model = controladorPersonaje.equiparCania(session,objeto.getIdObjeto());
    assertThat(model.getViewName(), equalToIgnoringCase("objetoDelJugador.html"));
}

@Test
    public void dadoQueEstoyEnLaVistaPersonajeYelJugadorEsNullQueCuandoToqueElBotonCaniaMeDevuelvaLaVistaPersonaje(){
    when(repositorioJugador.buscarJugador(1L)).thenReturn(jugador);
    when(servicioJugador.equipaCaniaAPersonaje(jugador,objeto.getIdObjeto())).thenReturn(true);
    ModelAndView model = controladorPersonaje.equiparCania(session,objeto.getIdObjeto());
    assertThat(model.getViewName(), equalToIgnoringCase("vistaPersonaje.html"));
}

   /* @Test
    public void dadoQueElJugadorEstaLogueadoQueSePuedaEquiparUnaCaña(){//cuando toco en la parte de caña que me lleve a la vista /caña y que tenga las cañas disponibles para equipar
       // jugador.agregarObjeto(objeto);
       // jugador.agregarObjeto(objeto);
        //servicioJugador.equipaCaniaAPersonaje(jugador,objeto.getIdObjeto());
        when(servicioJugador.equipaCaniaAPersonaje(jugador,objeto.getIdObjeto())).thenReturn(true);
        when(session.getAttribute("jugador")).thenReturn(jugador);
       // when(servicioJugador.equipaCaniaAPersonaje(any(Jugador.class),anyInt())).thenReturn(true);
//cuando toco personaje estoy en personaje pero despues tengo que tener otra vista equipamiento
       ModelAndView model = controladorPersonaje.equiparCania(session,1); //lo comento porque comente el metodo
       assertThat(model.getViewName(), equalToIgnoringCase("vistaEquipamiento.html"));



    }*/
}
