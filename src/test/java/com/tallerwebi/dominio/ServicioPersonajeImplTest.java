package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Objeto;
import com.tallerwebi.dominio.excepcion.ObjetoInexistenteException;
import com.tallerwebi.dominio.excepcion.ParametroInvalidoException;
import com.tallerwebi.dominio.repositorio.RepositorioJugador;
import com.tallerwebi.dominio.repositorio.RepositorioObjeto;
import com.tallerwebi.dominio.servicio.ServicioJugadorImpl;
import com.tallerwebi.dominio.servicio.ServicioTiendaImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
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

   /* @Test
    public void queAlReclamarMonedasSeLeAgreguenALasMonedasDelJugador(){
        jugador.setId(1L);
        jugador.setMonedas(200.0);
       when(repositorioJugador.buscarjugadorPorId(1L)).thenReturn(jugador);
        servicioTienda.reclamarMonedas(jugador.getId());
       Double valorEsperado = 400.0;
       Double valorObtenido = jugador.getMonedas();
       assertEquals(valorEsperado,valorObtenido);
    }

    @Test
    public void queElJugadorPuedaReclamarMonedasSiNuncaReclamoAntes(){
       jugador.setId(1L);
       jugador.setUltimoReclamoDeMonedas(null);
       assertTrue(servicioTienda.puedeReclamarMonedas(jugador));
    }

    @Test
    public void quePuedaReclamarSiPasaronMasDe15Segundos(){
       jugador.setId(1L);
       jugador.setUltimoReclamoDeMonedas(LocalDateTime.now().minusSeconds(15));
       assertTrue(servicioTienda.puedeReclamarMonedas(jugador));
    }

    @Test
    public void queNoPuedaReclamarSiPasaronMenosDe15Segundos(){
       jugador.setId(1L);
       jugador.setUltimoReclamoDeMonedas(LocalDateTime.now().minusSeconds(10));
       assertFalse(servicioTienda.puedeReclamarMonedas(jugador));
    }*/
}
