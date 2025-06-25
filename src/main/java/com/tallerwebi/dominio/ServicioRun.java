package com.tallerwebi.dominio;

import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ServicioRun {

    void jugarTurno(HttpSession httpSession);
    Boolean hayCeboJugador(Run run);

    List<Pez> obtenerPecesPescados(Run run);

    Double calcularGanancia(Run run);

    Integer getCantidadTurnosJugados(Run run);
}
