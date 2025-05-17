package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioRun {

    List<Pez> generarPeces();
    Pez devolverPezSeleccionado(List<Pez> pecesDelTurno, Integer posicionDelPez);
    Integer obtenerNumeroDePezSeleccionado();
    Boolean atrapar (Pez pez);
    void jugarTurno(Run run);
}
