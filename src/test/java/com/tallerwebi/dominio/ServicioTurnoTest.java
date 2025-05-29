package com.tallerwebi.dominio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

public class ServicioTurnoTest {

    private ServicioTurnoImpl st;
    private Run run;
    private Mar mar;

    @BeforeEach
    public void setUp() {
        st = new ServicioTurnoImpl();

    }

    @Test
    public void crearTurno() {
        mar = new Mar("Nombre"); //
        int cantidadDeCebo = 3;
        run = new Run(mar, cantidadDeCebo);

        Turno turno = st.crearUnTurno(run);

        assertNotNull(turno);
    }


    @Test
    public void crearTurnoSoloSiHayCebo() {
        mar = new Mar("Nombre"); //
        int cantidadDeCebo = 3;
        run = new Run(mar, cantidadDeCebo);

        Turno turno = st.crearUnTurno(run);

        assertNotNull(turno);
    }

    @Test
    public void nocrearTurnoSiCeboEsMenorIgualACero() {
        mar = new Mar("Nombre"); //
        int cantidadDeCebo = 0;
        run = new Run(mar, cantidadDeCebo);

        ParametroInvalidoException ex = assertThrows(
                ParametroInvalidoException.class,
                () -> st.crearUnTurno(run),
                "Debería lanzar excepción si no hay cebo");

        assertEquals("No hay cebo para crear un turno.", ex.getMessage());
    }

}
