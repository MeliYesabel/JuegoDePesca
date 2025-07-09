package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Pez;
import com.tallerwebi.dominio.entidad.Run;
import com.tallerwebi.dominio.servicio.ServicioRun;
import com.tallerwebi.dominio.servicio.ServicioTurno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ControladorTurnoTest {

    private ServicioTurno servicioTurno;
    private ServicioRun servicioRun;
    private ControladorTurno controlador;
    private HttpSession session;
    private ModelMap model;
    private Run run;
    private Pez pez;

    @BeforeEach
    public void setUp() {
        servicioTurno = mock(ServicioTurno.class);
        servicioRun = mock(ServicioRun.class);
        session = mock(HttpSession.class);
        model = new ModelMap();
        controlador = new ControladorTurno(servicioRun, servicioTurno);
        run = mock(Run.class);
        pez = new Pez();
    }

    @Test
    public void testSeleccionarPez_pescoTrue_agregaPezYActualizaSesion() {
        Long idPez = 1L;

        when(session.getAttribute("run")).thenReturn(run);
        when(servicioTurno.pescarPezPorId(idPez)).thenReturn(pez);
        when(servicioTurno.pesca(pez)).thenReturn(true);

        ModelAndView mav = controlador.seleccionarPez(idPez, model, session);

        assertEquals("pezPescadoVista", mav.getViewName());
        assertEquals(true, model.get("pesco"));
        assertEquals(pez, model.get("pez"));

        verify(run).agregarPecesPescados(pez);
        verify(session).setAttribute("run", run);
        verify(session).removeAttribute("turnoActual");
    }

    @Test
    public void testSeleccionarPez_pescoFalse_noAgregaNiActualizaRun() {
        Long idPez = 2L;

        when(session.getAttribute("run")).thenReturn(run);
        when(servicioTurno.pescarPezPorId(idPez)).thenReturn(pez);
        when(servicioTurno.pesca(pez)).thenReturn(false);


        ModelAndView mav = controlador.seleccionarPez(idPez, model, session);

        assertEquals("pezPescadoVista", mav.getViewName());
        assertEquals(false, model.get("pesco"));
        assertEquals(pez, model.get("pez"));

        verify(run, never()).agregarPecesPescados(any());
        verify(session, never()).setAttribute(eq("run"), any());
        verify(session).removeAttribute("turnoActual");
    }
}
