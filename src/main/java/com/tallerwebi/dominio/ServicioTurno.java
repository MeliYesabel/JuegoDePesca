package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioTurno {
    List<Pez> generarPeces(Mar mar);
    boolean atrapar(Pez pez);
    Pez devolverPezSeleccionado(List<Pez> pecesDelTurno, Integer posicionDelPez);
    Integer obtenerNumeroDePezSeleccionado();
}
