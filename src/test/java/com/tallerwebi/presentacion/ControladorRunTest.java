package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Mar;
import com.tallerwebi.dominio.entidad.Run;
import com.tallerwebi.dominio.servicio.ServicioMapa;
import com.tallerwebi.dominio.servicio.ServicioRun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControladorRunTest {

    private ServicioMapa servicioMapaMock;
    private ServicioRun servicioRunMock;
    private ControladorRun controladorRun;
    private HttpSession session;
    private ServicioRun servicioRun;
    private ServicioMapa servicioMapa;
    private ControladorRun controlador;
    private Jugador jugador;
    private Mar mar;
    private Run run;

    @BeforeEach
    public void setUp() {
        servicioRunMock = mock(ServicioRun.class);
        servicioMapaMock = mock(ServicioMapa.class);
        controladorRun = new ControladorRun(servicioRunMock, servicioMapaMock);
        session = mock(HttpSession.class);

        jugador = new Jugador();
        jugador.setCeboEquipado(2);

        run = new Run();
        run.setId(1L);
        run.setJugador(jugador);
        run.setCebo(2);

        mar = new Mar();
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

    @Test
    public void testIniciarRun_creaRunYRedirigeAVistaRun() {
        Mar mar = new Mar();
        mar.setId_mar(1L);
        Long idMar = 1L;

        when(session.getAttribute("jugador")).thenReturn(jugador);
        when(servicioMapaMock.obtenerMarPorId(1L)).thenReturn(mar);

        ModelAndView mav = controladorRun.iniciarRun(idMar, session);

        assertEquals("vistaRun", mav.getViewName());

        Run runCreado = (Run) mav.getModel().get("run");
        assertEquals(jugador, runCreado.getJugador());
        assertEquals(mar, runCreado.getMar());
        assertEquals(3, runCreado.getCebo());

        verify(servicioRunMock).guardarRun(runCreado);
        verify(session).setAttribute("run", runCreado);
    }

}

