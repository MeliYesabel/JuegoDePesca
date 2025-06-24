package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.ObjetoInexistenteException;
import com.tallerwebi.dominio.excepcion.ParametroInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioPersonajeImplTest {

    ServicioTiendaImpl servicioTienda;
    ServicioJugadorImpl servicioJugador;
    Jugador jugador;
    Objeto objeto;
    RepositorioObjeto repositorioObjeto;
    RepositorioJugador repositorioJugador;

   @BeforeEach
   public void setUp() {
       repositorioObjeto = mock(RepositorioObjeto.class);
       repositorioJugador = mock(RepositorioJugador.class);
       jugador = new Jugador();
       objeto = new Objeto(100.0,"caña metal");

       servicioTienda = new ServicioTiendaImpl(repositorioObjeto,repositorioJugador);
       servicioJugador = new ServicioJugadorImpl(repositorioJugador,repositorioObjeto);
   }


@Test
    public void queSePuedaEquiparUnaCaniaSiElJugadorLaTiene(){
       jugador.setMonedas(200);
       jugador.setId(1L);
    when(repositorioJugador.buscarJugador(1L)).thenReturn(jugador);
        when(repositorioObjeto.buscarObjeto(1L)).thenReturn(objeto);
        servicioTienda.agregarObjetoDisponible(objeto);
        servicioTienda.comprarObjeto(jugador,1L);
       Boolean equipada = servicioJugador.equipaCaniaAPersonaje(jugador,1L);

       assertTrue(equipada);



    }

    @Test
   public void queNoSePuedaEquiparUnaCaniaSiElJugadorOelIdDelObjetoEsNull(){

       // servicioJugador.equipaCaniaAPersonaje(jugador,1L);


        assertThrows(ParametroInvalidoException.class, ()->
    servicioJugador.equipaCaniaAPersonaje(jugador,1L));
    }

    @Test
    public void queNoSePuedaEquiparUnaCaniaSiElJugadorNoLaTiene(){
        jugador.setMonedas(200);
        jugador.setId(1L);
        when(repositorioJugador.buscarJugador(1L)).thenReturn(jugador);
        when(repositorioObjeto.buscarObjeto(1L)).thenReturn(objeto);
        servicioTienda.agregarObjetoDisponible(objeto);

        assertThrows(ObjetoInexistenteException.class, ()->
                servicioJugador.equipaCaniaAPersonaje(jugador,1L));

    }
}
