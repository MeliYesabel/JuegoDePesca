package com.tallerwebi.dominio;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioMapaTest {

    ServicioMapa servicioMapa;
    RepositorioMar repositorioMar = mock(RepositorioMar.class);

    @BeforeEach
    public void init() {
        servicioMapa = new ServicioMapaImplement(repositorioMar);
    }


    @Test
    public void obtenerElEstadoMarSegunElJugadorQueMeDeVerDeElEstadoEstaBloqueado() {
        Mar mar = new Mar("Poseidon",0.0,"Mitologia Griega",true);

        Jugador jugador = new Jugador("Anahi","anis",30.0,1);

        JugadorMar jugadorMar= new JugadorMar(jugador,mar,true);

        when(repositorioMar.obtenerElEstadoMarDelJugador(mar,jugador)).thenReturn(true);

        boolean estado = servicioMapa.obtenerElEstadoDelMarSegunELJugador(mar,jugador);

        assertThat(estado,is(true));

    }


    /*esta clase deberia ir en el repositorio preguntar a la prefe con respecto al mock
    @Test
    public void queSePuedaObtenerUnMarPorNombre() {
        //given
        Mar marBuscar = new Mar("Mitologia griega", 0.0, "mar uno", false);
        //configuracion del mock
        when(repositorioMar.obtenerMarPorNombre(marBuscar.getNombre())).thenReturn(marBuscar);

        // when
        Mar mar = whenObtenerElEstadoDeUnMarPorNombre(marBuscar.getNombre());

        //then
        assertNotNull(mar);
        assertEquals("Mitologia griega", mar.getNombre());
    }*/

  /*  @Test
    public void queSePuedaObtenerElEstadoDeUnMarEspecifico(){
        Mar marBuscar = new Mar("Mitologia griega", 0.0, "mar uno", false);
        Mar mar = whenObtenerElEstadoDeUnMarPorNombre(marBuscar.getNombre());
        thenComprobarResultadoFueExitosoONo(mar,"Mitologia griega");
    }

    private void thenComprobarResultadoFueExitosoONo(Mar mar, String nombre) {
         assertThat(mar.getNombre(),equals(nombre));
    }*/

    private Mar whenObtenerElEstadoDeUnMarPorNombre(String nombre) {
        return servicioMapa.obtenerElEstadoDeUnMarPorNombre(nombre);
    }

  /*  @Test
    public void siElJugadorNOTieneSuficienteMonedasParaDesbloquearUnElMarFalle() {
        Boolean sePuedoComprar = whenCalcularSiSePuedeComprarElDesbloqueoDelMar("alias_jugador",90.0);
        thenElCalculoNofueExitoso(sePuedoComprar);
    }

    @Test
    public void siElJugadorTieneSuficienteMonedasParaDesbloquearUnElMarExitoso() {
       Boolean sePuedoComprar = whenCalcularSiSePuedeComprarElDesbloqueoDelMar("alias_jugador",150.0);
       thenElCalculofueExitoso(sePuedoComprar);
    }

    private Boolean whenCalcularSiSePuedeComprarElDesbloqueoDelMar(String aliasJugador, Double monedas) {
          return sm.calcularSiSePuedeDesbloquearUnMar(aliasJugador,monedas);
    }

    private void thenElCalculoNofueExitoso(Boolean sePuedoComprar) {
        assertThat(sePuedoComprar, is(false));
    }

    private void thenElCalculofueExitoso(Boolean sePuedoComprar) {
        assertThat(sePuedoComprar,is(true));
    }*/
}
