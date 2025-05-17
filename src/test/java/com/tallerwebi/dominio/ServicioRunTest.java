package com.tallerwebi.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ServicioRunTest {

    private ServicioRun servicioRun;
    private List<Pez> pecesGenerados;

    @BeforeEach
    public void setUp() {
        Mar mar = new Mar();
        mar.setPeces(List.of(
                new Pez("Pez1"), new Pez("Pez2"), new Pez("Pez3"), new Pez("Pez4"), new Pez("Pez5"),
                new Pez("Pez6"), new Pez("Pez7"), new Pez("Pez8"), new Pez("Pez9"), new Pez("Pez10")
        ));
        servicioRun = new ServicioRunImpl(mar);
    }

    @Test
    public void deberiaGenerarTresPecesParaElTurno() {
        givenNoHayPecesGenerados();
        whenSeGeneranLosPeces();
        thenSeObtienenTresPeces();
    }

    private void givenNoHayPecesGenerados() {
        pecesGenerados = null;
    }

    private void whenSeGeneranLosPeces() {
        pecesGenerados = servicioRun.generarPeces();
    }

    private void thenSeObtienenTresPeces() {
        assertNotNull(pecesGenerados);
        assertEquals(3, pecesGenerados.size());
    }

}
