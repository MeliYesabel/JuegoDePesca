package com.tallerwebi.dominio;

import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ServicioMapaTest {

    /*para crear un test de servicio*/
    /* primero : necesito la interfaz y el la clase servicio */

    ServicioMapa sm = new ServicioMapaImplement();

    /*DUDA: las excepciones solo pueden ir en los controller test o tambien pueden ir
    * en servicios test y segundo validar que el usuario tenga alguna moneda es del controller y es de servicio*/

    @Test
    public void siElJugadorNOTieneSuficienteMonedasParaDesbloquearUnElMarFalle() {
        Boolean sePuedoComprar = whenCalcularSiSePuedeComprarElDesbloqueoDelMar("alias_jugador",90.0);
        thenElCalculoNofueExitoso(sePuedoComprar);
    }

    @Test
    public void siElJugadorTieneSuficienteMonedasParaDesbloquearUnElMarExitoso() {
       Boolean sePuedoComprar = whenCalcularSiSePuedeComprarElDesbloqueoDelMar("alias_jugador",150.0);
       thenElCalculofueExitoso(sePuedoComprar);
    }

    /**/
    private Boolean whenCalcularSiSePuedeComprarElDesbloqueoDelMar(String aliasJugador, Double monedas) {
          return sm.calcularSiSePuedeDesbloquearUnMar(aliasJugador,monedas);
    }

    private void thenElCalculoNofueExitoso(Boolean sePuedoComprar) {
        assertThat(sePuedoComprar, is(false));
    }

    private void thenElCalculofueExitoso(Boolean sePuedoComprar) {
        assertThat(sePuedoComprar,is(true));
    }
}
