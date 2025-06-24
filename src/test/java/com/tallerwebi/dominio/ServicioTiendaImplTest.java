package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.MonedasInsuficientesException;
import com.tallerwebi.dominio.excepcion.ObjetoInexistenteException;
import com.tallerwebi.dominio.excepcion.ParametroInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ServicioTiendaImplTest {

    private Jugador jugador;
    private Objeto objeto;
    private ServicioTiendaImpl servicioTienda;
    private RepositorioObjeto repositorioObjeto;
    private RepositorioJugador repositorioJugador;

    @BeforeEach
    public void init(){
        jugador = new Jugador();
        objeto = new Objeto(100.0,"caña metal");
        //objeto.setIdObjeto(1);
        repositorioObjeto = mock(RepositorioObjeto.class);
        repositorioJugador = mock(RepositorioJugador.class);
        servicioTienda = new ServicioTiendaImpl(repositorioObjeto,repositorioJugador);
       // servicioTienda = mock(ServicioTiendaImpl.class);

    }





    @Test
    public void queSePuedaComprarUnObjetoSiTieneMonedasSuficientes() {

        jugador.setMonedas(150.0);
        //objeto.setIdObjeto(1);
      when(repositorioObjeto.buscarObjeto(1L)).thenReturn(objeto);
        // sin mock
        servicioTienda.agregarObjetoDisponible(objeto);
       // servicioTienda.agregarYGuardarObjeto(objeto);

        Boolean resultado = servicioTienda.comprarObjeto(jugador, 1L);

        assertTrue(resultado);
        //assertThat(resultado).isFalse();



    }

    @Test
    public void queNoSePuedaComprarUnObjetoSiNoTieneMonedasSuficientes() {
        jugador.setMonedas(50.0); // menos que el precio del objeto

        servicioTienda.agregarObjetoDisponible(objeto); // agrega el objeto a la tienda
when(repositorioObjeto.buscarObjeto(1L)).thenReturn(objeto);
        assertThrows(MonedasInsuficientesException.class, () -> {
            servicioTienda.comprarObjeto(jugador, 1L);
        });
    }

    @Test
    public void queNoSePuedaComprarUnObjetoQueNoExiste() {
        jugador.setMonedas(150.0); // monedas suficientes
when(repositorioObjeto.buscarObjeto(1L)).thenReturn(objeto);
        // No agrego el objeto a la tienda
        assertThrows(ObjetoInexistenteException.class, () -> {
            servicioTienda.comprarObjeto(jugador, 2L); // objeto no agregado objeto.getIdObjeto()
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
