package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.JugadorMar;
import com.tallerwebi.dominio.entidad.Mar;
import com.tallerwebi.dominio.repositorio.RepositorioMar;
import com.tallerwebi.dominio.servicio.ServicioMapa;
import com.tallerwebi.dominio.servicio.ServicioMapaImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ServicioMapaTest {

    ServicioMapa servicioMapa;
    RepositorioMar repositorioMar = mock(RepositorioMar.class);

    @BeforeEach
    public void init() {
        servicioMapa = new ServicioMapaImplement(repositorioMar);
    }

    @Test
    public void queSiElJugadorSuperaLaCantidadDeMonedasDelCostoDelMArPuedaComprarlo(){
        //given
        Mar mar = new Mar("Poseidon",100.0,"Mitologia Griega",true);
        Jugador jugador = new Jugador("Anahi","anis",300.0,1);
        JugadorMar jugadorMar= new JugadorMar(jugador,mar,true);

        when(repositorioMar.obtenerElJugadorMarBuscado(mar, jugador)).thenReturn(jugadorMar);

        //when
        Boolean estado = servicioMapa.desbloquearMarSegunElJugador(mar,jugador);

        //then
        assertThat(estado,is(true));
    }

    @Test
    public void queSiElJugadorTieneLaMismaCantidadDeMonedasQueElCostoDelMArPuedaComprarlo(){
        //given
        Mar mar = new Mar("Poseidon",100.0,"Mitologia Griega",true);
        Jugador jugador = new Jugador("Anahi","anis",100.0,1);
        JugadorMar jugadorMar= new JugadorMar(jugador,mar,true);

        when(repositorioMar.obtenerElJugadorMarBuscado(mar, jugador)).thenReturn(jugadorMar);

        //when
        Boolean estado = servicioMapa.desbloquearMarSegunElJugador(mar,jugador);

        //then
        assertThat(estado,is(true));
    }

    @Test
    public void queNOSePuedaCambiarElEstadoDeUnMarSiElJugadorNOTieneSuficientesMonedas(){
        //given
        Mar mar = new Mar("Poseidon",200.0,"Mitologia Griega",true);
        Jugador jugador = new Jugador("Anahi","anis",100.0,1);
        JugadorMar jugadorMar= new JugadorMar(jugador,mar,true);

        when(repositorioMar.obtenerElJugadorMarBuscado(mar, jugador)).thenReturn(jugadorMar);

        //when
        Boolean estado = servicioMapa.desbloquearMarSegunElJugador(mar,jugador);

        //then
        assertThat(estado,is(false));
    }

    @Test
    public void queSePuedaCambiarElEstadoDeUnJugadorMarBloqueadoADesbloqueado(){
        //given
        Mar mar = new Mar("Poseidon",200.0,"Mitologia Griega",true);
        Jugador jugador = new Jugador("Anahi","anis",100.0,1);
        JugadorMar jugadorMar= new JugadorMar(jugador,mar,true);

        when(repositorioMar.obtenerElJugadorMarBuscado(mar, jugador)).thenReturn(jugadorMar);

        // when
        Boolean desbloqueo = servicioMapa.cambiarElEstadoDelMarADesbloqueado(mar,jugador);

        // then
        assertThat(desbloqueo,is(true));
        assertThat(jugadorMar.getEstadoBloqueado(),is(false));
    }

    @Test
    public void queSePuedaDescontarLasMonedasDelJugadorCuandoCompreUnMar(){
        Mar mar = new Mar("Poseidon",200.0,"Mitologia Griega",true);
        Jugador jugador = new Jugador("Anahi","anis",300.0,1);

        Double precio = servicioMapa.descontarLasMonedasDelJugador(mar,jugador);

        assertThat(precio,is(100.0));
        assertThat(jugador.getMonedas(),is(100.0));
    }

    @Test
    public void obtenerElEstadoMarSegunElJugadorQueMeDeVerDeElEstadoEstaBloqueado() {
        //given
        Mar mar = new Mar("Poseidon",0.0,"Mitologia Griega",true);

        Jugador jugador = new Jugador("Anahi","anis",30.0,1);

     //   JugadorMar jugadorMar= new JugadorMar(jugador,mar,true);

        when(repositorioMar.obtenerElEstadoMarDelJugador(mar,jugador)).thenReturn(true);

        //when
        boolean estado = servicioMapa.obtenerElEstadoDelMarSegunELJugador(mar,jugador);

        //then
        assertThat(estado,is(true));
    }

    @Test
    public void obtenerMarPorNombre() {
        Mar mar =   new Mar("Mitologia griega", 0.0, "mar uno", false);

        when(repositorioMar.obtenerMarPorNombre("Mitologia griega")).thenReturn(mar);

        Mar obtenido = servicioMapa.obtenerUnMarPorNombre("Mitologia griega");
        assertEquals(mar, obtenido);
    }

    @Test
    public void obtenerTodaListaDeMares() {
        List<Mar>mares = givenInstanciaDeTodosLosMares();

        //mock
        when(repositorioMar.obtenerLaListaCompletaDeTodosLosMares()).thenReturn(mares);

        List<Mar>resultado = servicioMapa.obtenerTodaListaDeMares();

        thenComporbarQueElTestDeVerdadero(resultado,mares);
    }

    private void thenComporbarQueElTestDeVerdadero(List<Mar> resultado, List<Mar> comparar) {
        assertThat(resultado, is(comparar));
        assertThat(resultado.size(), is(7));
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

}
