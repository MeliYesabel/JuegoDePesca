package com.tallerwebi.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ServicioRunTest {

    private ServicioRun servicioRun;
    private List<Pez> pecesGenerados;
    private Run run;
    @BeforeEach
    public void cofiguracionDeDatosNecesarios() {
        Mar mar = new Mar();
        mar.setPeces(List.of(
                new Pez("Pez1", Rareza.NORMAL), new Pez("Pez2", Rareza.NORMAL), new Pez("Pez3", Rareza.NORMAL), new Pez("Pez4", Rareza.NORMAL), new Pez("Pez5", Rareza.NORMAL),
                new Pez("Pez6", Rareza.NORMAL), new Pez("Pez7", Rareza.NORMAL), new Pez("Pez8", Rareza.NORMAL), new Pez("Pez9", Rareza.NORMAL), new Pez("Pez10", Rareza.NORMAL)
        ));
        servicioRun = new ServicioRunImpl(mar);
        run = new Run(mar);
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

    @Test
    public void deberiaRestarCeboEnRun() {
        run.setCebo(3); // se elegin 3 cebos para la partida
        servicioRun.jugarTurno(run); // se juega el turno, aparecen 3 peces, se elige uno, se sabe si si o no, !! se resta 1 cebo !!
        assertEquals(2, run.getCebo());
    }

}
