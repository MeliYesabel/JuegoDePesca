package com.tallerwebi.dominio;
import com.tallerwebi.dominio.excepcion.ObjetoInexistenteException;
import com.tallerwebi.dominio.excepcion.ParametroInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Disabled
    @Test
    public void tomarTresPecesDelLalistaDePecesDelMar(){
        mar = new Mar("Nombre");
        agregarPeceslaLista(mar);
        int cantidadDeCebo = 1;
        run = new Run(mar, cantidadDeCebo);
        Turno tr = st.crearUnTurno(run);

       tr.setPecesGenerados(st.tomarTresPecesParaElTurno(run));

        List<Pez> testTresPeces = tr.getPecesGenerados();

        assertNotNull(testTresPeces);
        assertEquals(3, tr.getPecesGenerados().size());
    }

    @Disabled
    @Test
    public void obtenerParaPescarElPezEnlaPosicionCero(){
        mar = new Mar("Nombre");
        agregarPeceslaLista(mar);
        int cantidadDeCebo = 1;
        run = new Run(mar, cantidadDeCebo);
        Turno tr = st.crearUnTurno(run);

        tr.setPecesGenerados(st.tomarTresPecesParaElTurno(run));

        List<Pez> testTresPeces = tr.getPecesGenerados();

        Pez pezSeleccionado = st.seleccionarUnPez(testTresPeces, 0);

        assertEquals(testTresPeces.get(0), pezSeleccionado);
    }

    @Disabled
    @Test
    public void noPoderObtenerParaPescarElPezEnlaPosicionCuatro() {
        mar = new Mar("Nombre");
        agregarPeceslaLista(mar);
        int cantidadDeCebo = 1;
        run = new Run(mar, cantidadDeCebo);
        Turno tr = st.crearUnTurno(run);

        tr.setPecesGenerados(st.tomarTresPecesParaElTurno(run));
        List<Pez> testTresPeces = tr.getPecesGenerados();

        assertThrows(ObjetoInexistenteException.class, () -> {
            st.seleccionarUnPez(testTresPeces, 4);
        });
    }

    public void agregarPeceslaLista(Mar mar){
        List<Pez> peces = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            peces.add(new Pez());
        }
        mar.getPeces().addAll(peces);
    }


}
