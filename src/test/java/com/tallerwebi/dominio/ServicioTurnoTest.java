package com.tallerwebi.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioTurnoTest {

    private ServicioTurno servicioTurno;
    private List<Pez> pecesGenerados;
    private Run run;
    @BeforeEach
    public void cofiguracionDeDatosNecesarios() {
        Mar mar = new Mar();
        mar.setPeces(List.of(
                new Pez("Pez1", Rareza.NORMAL), new Pez("Pez2", Rareza.NORMAL), new Pez("Pez3", Rareza.NORMAL), new Pez("Pez4", Rareza.NORMAL), new Pez("Pez5", Rareza.NORMAL),
                new Pez("Pez6", Rareza.NORMAL), new Pez("Pez7", Rareza.NORMAL), new Pez("Pez8", Rareza.NORMAL), new Pez("Pez9", Rareza.NORMAL), new Pez("Pez10", Rareza.NORMAL)
        ));
        servicioTurno = new ServicioTurnoImpl();
        run = new Run(mar);
    }

    @Test
    public void deberiaGenerarTresPeces() {
        List<Pez> peces = servicioTurno.generarPeces(run.getMar());
        assertEquals(3, peces.size());
    }
}
