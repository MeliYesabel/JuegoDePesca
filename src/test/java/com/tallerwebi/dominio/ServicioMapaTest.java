package com.tallerwebi.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

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


    @Test
    public void obtenerElEstadoMarSegunElJugadorQueMeDeVerDeElEstadoEstaBloqueado() {
        //given
        Mar mar = new Mar("Poseidon",0.0,"Mitologia Griega",true);

        Jugador jugador = new Jugador("Anahi","anis",30.0,1);

        JugadorMar jugadorMar= new JugadorMar(jugador,mar,true);

        when(repositorioMar.obtenerElEstadoMarDelJugador(mar,jugador)).thenReturn(true);

        //when
        boolean estado = servicioMapa.obtenerElEstadoDelMarSegunELJugador(mar,jugador);

        //then
        assertThat(estado,is(true));

    }

    private Mar whenObtenerElEstadoDeUnMarPorNombre(String nombre) {
        return servicioMapa.obtenerElEstadoDeUnMarPorNombre(nombre);
    }

}
