package com.tallerwebi.dominio;

import org.springframework.ui.ModelMap;

import java.util.List;

public interface ServicioRun {
    Boolean hayCebo(Turno turno);
    String jugarTurno(ModelMap model);

    List<Pez> obtenerPecesPescados(Run run);

    Double calcularGanancia(Run run);

    Integer getCantidadTurnosJugados(Run run);
}
