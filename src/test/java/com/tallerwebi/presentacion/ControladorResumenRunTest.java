package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Pez;
import com.tallerwebi.dominio.entidad.Run;
import com.tallerwebi.presentacion.ControladorResumenRun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControladorResumenRunTest {

    private ControladorResumenRun controlador;
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        controlador = new ControladorResumenRun();
        session = mock(HttpSession.class);
    }

    @Test
    public void testMostrarResumen_sinRun_redirigeAVistaError() {
        when(session.getAttribute("run")).thenReturn(null);

        ModelAndView mav = controlador.mostrarResumen(session);

        assertEquals("errorVista", mav.getViewName());
        assertEquals("No hay una partida activa. Volvé al inicio.", mav.getModel().get("error"));
    }

    @Test
    public void testMostrarResumen_conRun_muestraResumenCorrecto() {
        Run run = mock(Run.class);
        List<Pez> peces = Arrays.asList(new Pez(), new Pez(), new Pez());

        when(session.getAttribute("run")).thenReturn(run);
        when(run.obtenerganacia()).thenReturn(150);
        when(run.getPecesPescados()).thenReturn(peces);

        ModelAndView mav = controlador.mostrarResumen(session);

        assertEquals("vistaResumen", mav.getViewName());
        assertEquals(150, mav.getModel().get("ganancia"));
        assertEquals(3, mav.getModel().get("cantidadPeces"));
    }

    @Test
    public void testMostrarResumen_conRunSinGananciaNiPeces() {
        Run run = mock(Run.class);

        when(session.getAttribute("run")).thenReturn(run);
        when(run.obtenerganacia()).thenReturn(null);
        when(run.getPecesPescados()).thenReturn(Collections.emptyList());

        ModelAndView mav = controlador.mostrarResumen(session);

        assertEquals("vistaResumen", mav.getViewName());
        assertEquals(0, mav.getModel().get("ganancia"));
        assertEquals(0, mav.getModel().get("cantidadPeces"));
    }
}

