package com.tallerwebi.dominio;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioMapaTest {

    /*Test Servicio : se necesita la interfaz y el la clase servicio que implementa  */
    // cada vez que tengo un servicio que este inyectadi el repo
    private RepositorioMar repositorioMar ;
    private ServicioMapa servicioMapa;

    @BeforeEach
    public void setUp() {
        repositorioMar = mock(RepositorioMar.class);
        servicioMapa = new ServicioMapaImplement(repositorioMar);
    }




    /*esta clase deberia ir en el repositorio preguntar a la prefe con respecto al mock
    @Test
    public void queSePuedaObtenerElEstadoDeUnMarEspecificoSiEstaBloqueadoMensajeError(){

    }*/

    @Test
    public void comprobarQueElEstadoDelMarGriegoEsfalso(){
        Mar marBuscar = repositorioMar.obtenerMarPorNombre("Mitologia griega");
        Mar nuevoMar = new Mar("Mitologia griega", 0.0, "mar uno", false);
        when(repositorioMar.obtenerMarPorNombre("Mitologia griega")).thenReturn(nuevoMar);
        boolean estado = marBuscar.getEstadoBloqueado();
        assertThat(estado,is(false));

    }

  /*  @Test
    public void queSePuedaObtenerElEstadoDeUnMarEspecifico(){
        Mar marBuscar = new Mar("Mitologia griega", 0.0, "mar uno", false);
        Mar mar = whenObtenerElEstadoDeUnMarPorNombre(marBuscar.getNombre());
        thenComprobarResultadoFueExitosoONo(mar,"Mitologia griega");
    }

    private void thenComprobarResultadoFueExitosoONo(Mar mar, String nombre) {
         assertThat(mar.getNombre(),equals(nombre));
    }

    private Mar whenObtenerElEstadoDeUnMarPorNombre(String nombre) {
        return servicioMapa.obtenerElEstadoDeUnMarPorNombre(nombre);
    }*/

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
