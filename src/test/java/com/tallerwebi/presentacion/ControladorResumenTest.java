package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.Pez;
import com.tallerwebi.dominio.Run;
import com.tallerwebi.dominio.ServicioRun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorResumenTest {

    private ServicioRun servicioRunMock;
    private ControladorResumenRun controladorResumen;
    private HttpSession session;

    private Run run;
    private Jugador jugador;

    @BeforeEach
    public void setUp() {
        servicioRunMock = mock(ServicioRun.class);
        controladorResumen = new ControladorResumenRun();
        session = mock(HttpSession.class);

        jugador = new Jugador();
        jugador.setCeboEquipado(2);

        run = new Run();
        run.setId(1L);
        run.setJugador(jugador);
        run.setCebo(2);
    }

    @Test
    public void mostrarResumenFinal_muestraDatosCorrectos() {

        List<Pez> peces = Arrays.asList(
                new Pez("Dorado"),
                new Pez("Bagre")
        );
        Double ganancia = 50.0;
        Integer turnos = 2;

        when(servicioRunMock.obtenerPecesPescados(run)).thenReturn(peces);
        when(servicioRunMock.calcularGanancia(run)).thenReturn(ganancia);
        when(servicioRunMock.getCantidadTurnosJugados(run)).thenReturn(turnos);

        ModelAndView mav = controladorResumen.mostrarResumen(session);

        assertEquals("vistaResumen.html", mav.getViewName());
    }
}
