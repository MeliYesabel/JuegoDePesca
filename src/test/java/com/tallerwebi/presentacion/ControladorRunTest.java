package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControladorRunTest {

    private ServicioRun servicioRunMock;
    private ControladorRun controladorRun;
    private HttpSession session;

    private Run run;
    private Jugador jugador;

    @BeforeEach
    public void setUp() {
        servicioRunMock = mock(ServicioRun.class);
        controladorRun = new ControladorRun(servicioRunMock);
        session = mock(HttpSession.class);

        jugador = new Jugador();
        jugador.setCeboEquipado(2);

        run = new Run();
        run.setId(1L);
        run.setJugador(jugador);
        run.setCebo(2);
    }

    @Test
    public void verificarCeboJugador_redirigeATurno_siHayCebo() {
        when(session.getAttribute("run")).thenReturn(run);
        when(servicioRunMock.hayCeboJugador(run)).thenReturn(true);

        ModelAndView mav = controladorRun.verificarCeboJugador(session);

        assertEquals("redirect:/turno", mav.getViewName());
    }

    @Test
    public void verificarCeboJugador_redirigeAResumen_siNoHayCebo() {
        when(session.getAttribute("run")).thenReturn(run);
        when(servicioRunMock.hayCeboJugador(run)).thenReturn(false);

        ModelAndView mav = controladorRun.verificarCeboJugador(session);

        assertEquals("redirect:/resumen", mav.getViewName());
    }

}

