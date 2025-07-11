package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.repositorio.RepositorioJugador;
import com.tallerwebi.dominio.repositorio.RepositorioObjeto;
import com.tallerwebi.dominio.servicio.ServicioJugador;
import com.tallerwebi.dominio.servicio.ServicioJugadorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioJugadorTest {

     ServicioJugador servicioJugador;
     /*pero le genera un mock del repo xq yo ya probe la implementacin en otro lado solo quiero usar un metodo para probar el test*/
     RepositorioJugador repositorioJugador = mock(RepositorioJugador.class);
     RepositorioObjeto repositorioObjeto = mock(RepositorioObjeto.class);
    @BeforeEach
    public void init() {
        /*genera un new xq ya si quiero probar la implementacion*/
        servicioJugador = new ServicioJugadorImpl(repositorioJugador,repositorioObjeto);
    }

    @Test //ver como puede funcionar a la abse de d atos
    public void siExisteJugadorPorIdqueSePuedaRetornarElJugador() {
        // Given
        Jugador j = new Jugador("Anahi", "anis", 100.0, 3);
        Long id = 1L; // Simula que este es su ID
        j.setId(id); // Asegúrate de tener un setter o constructor con ID

        // Configura el mock para que devuelva ese jugador cuando se lo busque
        when(repositorioJugador.buscarjugadorPorId(id)).thenReturn(j);

        // When
        Jugador resultado = servicioJugador.buscarJugadorPorId(id);

        // Then
        assertNotNull(resultado);
        assertEquals("Anahi", resultado.getNombre());
    }

}
