package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.servicio.ServicioSeleccion;
import com.tallerwebi.dominio.servicio.ServicioSeleccionImp;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ServicioSeleccionTest {

    ServicioSeleccion ss = new ServicioSeleccionImp();
    /*Setteo solo por ahora*/
    Jugador j = new Jugador();

    @Disabled
    @Test
    public void laCantidadDeCarnadaEsMenorACincoYMayorACeroTrue() {
        Boolean carnada = ss.laCantidadDeCarnadaEsMenorQueCindo(j.getCant_carnada());
        thenLaCantidadfueExitoso(carnada);
    }

    private void thenLaCantidadfueExitoso(Boolean carnada) {
        assertThat(carnada, is(true));
    }

    @Disabled
    @Test
    public void laCantidadDeCarnadaEsMayorQueCincoFalse() {
        j.setCant_carnada(6);
        Boolean carnada = ss.laCantidadDeCarnadaEsMenorQueCindo(j.getCant_carnada());
        thenLaCantidadNofueExitoso(carnada);
    }

    private void thenLaCantidadNofueExitoso(Boolean carnada) {
        assertThat(carnada,is(false));
    }
}
