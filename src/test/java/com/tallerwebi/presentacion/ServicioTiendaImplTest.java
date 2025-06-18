package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.Objeto;
import com.tallerwebi.dominio.excepcion.MonedasInsuficientesException;
import com.tallerwebi.dominio.excepcion.ObjetoInexistenteException;
import com.tallerwebi.dominio.excepcion.ParametroInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ServicioTiendaImplTest {

    private Jugador jugador;
    private Objeto objeto;
    private ServicioTiendaImpl servicioTienda;

    @BeforeEach
    public void init(){
        jugador = new Jugador();
        objeto = new Objeto(1,100.0);
        servicioTienda = new ServicioTiendaImpl();
    }





    @Test
    public void queSePuedaComprarUnObjetoSiTieneMonedasSuficientes() {

        jugador.setMonedas(150.0);


        // sin mock
        servicioTienda.agregarObjetoDisponible(objeto); // esto deberías implementarlo

        Boolean resultado = servicioTienda.comprarObjeto(jugador, objeto.getIdObjeto());

        assertTrue(resultado);
        //assertThat(resultado).isFalse();

    }

    @Test
    public void queNoSePuedaComprarUnObjetoSiNoTieneMonedasSuficientes() {
        jugador.setMonedas(50.0); // menos que el precio del objeto

        servicioTienda.agregarObjetoDisponible(objeto); // agregar el objeto a la "tienda"

        assertThrows(MonedasInsuficientesException.class, () -> {
            servicioTienda.comprarObjeto(jugador, objeto.getIdObjeto());
        });
    }

    @Test
    public void queNoSePuedaComprarUnObjetoQueNoExiste() {
        jugador.setMonedas(150.0); // monedas suficientes

        // No agrego el objeto a la tienda
        assertThrows(ObjetoInexistenteException.class, () -> {
            servicioTienda.comprarObjeto(jugador, objeto.getIdObjeto()); // objeto no agregado
        });
    }

    @Test
    public void queNoSePuedaComprarSiElJugadorEsNull() {
        assertThrows(ParametroInvalidoException.class, () -> {
            servicioTienda.comprarObjeto(null, objeto.getIdObjeto());
        });
    }

    @Test
    public void queNoSePuedaComprarSiElIdEsNull() {
        jugador.setMonedas(200.0);
        assertThrows(ParametroInvalidoException.class, () -> {
            servicioTienda.comprarObjeto(jugador, null);
        });
    }




}
