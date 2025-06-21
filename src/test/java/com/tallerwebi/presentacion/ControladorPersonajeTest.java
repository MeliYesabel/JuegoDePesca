package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.infraestructura.RepositorioJugadorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
    //controladorPersonaje = new ControladorPersonaje(servicioJugador,repositorioJugador);
    session = mock(HttpSession.class);

     }

}
