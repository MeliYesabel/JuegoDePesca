package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Mar;
import com.tallerwebi.dominio.entidad.Pez;
import com.tallerwebi.dominio.entidad.Run;
import com.tallerwebi.dominio.servicio.ServicioJugador;
import com.tallerwebi.dominio.servicio.ServicioMapa;
import com.tallerwebi.dominio.servicio.ServicioRun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControladorRunTest {

    private ServicioMapa servicioMapaMock;
    private ServicioRun servicioRunMock;
    private ServicioJugador servicioJugadorMock;
    private ControladorRun controladorRun;
    private HttpSession session;
    private Jugador jugador;
    private Run run;

    @BeforeEach
    public void setUp() {
        servicioRunMock = mock(ServicioRun.class);
        servicioMapaMock = mock(ServicioMapa.class);
        servicioJugadorMock = mock(ServicioJugador.class);
        controladorRun = new ControladorRun(servicioRunMock, servicioMapaMock,servicioJugadorMock);
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

    @Test
    public void testIniciarRun_creaRunYRedirigeAVistaRun() {
        Mar mar = new Mar();
        mar.setId_mar(1L);
        Long idMar = 1L;
        Long idUsuarioDto  = 3L;

        when(session.getAttribute("idUsuarioLogueado")).thenReturn(idUsuarioDto);
        when(servicioJugadorMock.buscarJugadorPorId(idUsuarioDto)).thenReturn(jugador);
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


    @Test
    public void testMostrarResumen_sinRun_redirigeAVistaError() {
        when(session.getAttribute("run")).thenReturn(null);

        ModelAndView mav = controladorRun.mostrarResumen(session);

        assertEquals("vistaSeleccion.html", mav.getViewName());
        assertEquals("No hay una partida activa. Volvé al inicio.", mav.getModel().get("error"));
    }

    /*@Test
    public void testMostrarResumen_conRun_muestraResumenCorrecto() {
        Run run = mock(Run.class);
        List<Pez> peces = Arrays.asList(new Pez(), new Pez(), new Pez());

        when(session.getAttribute("run")).thenReturn(run);
        when(run.obtenerganacia()).thenReturn(150);
        when(run.getPecesPescados()).thenReturn(peces);

        ModelAndView mav = controladorRun.mostrarResumen(session);

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

        ModelAndView mav = controladorRun.mostrarResumen(session);

        assertEquals("vistaResumen", mav.getViewName());
        assertEquals(0, mav.getModel().get("ganancia"));
        assertEquals(0, mav.getModel().get("cantidadPeces"));
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

        ModelAndView mav = controladorRun.mostrarResumen(session);

        assertEquals("vistaResumen.html", mav.getViewName());
    }*/
}

