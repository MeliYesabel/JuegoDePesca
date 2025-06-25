package com.tallerwebi.dominio;

import org.springframework.ui.ModelMap;

import java.util.List;

public interface ServicioRun {

    String jugarTurno(ModelMap model);
    Boolean hayCeboJugador(Run run);

    List<Pez> obtenerPecesPescados(Run run);

    Double calcularGanancia(Run run);

    Integer getCantidadTurnosJugados(Run run);
}
