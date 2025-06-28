package com.tallerwebi.dominio.servicio;

import com.tallerwebi.dominio.entidad.Pez;
import com.tallerwebi.dominio.entidad.Run;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ServicioRun {

    void jugarTurno(HttpSession httpSession);
    Boolean hayCeboJugador(Run run);

    List<Pez> obtenerPecesPescados(Run run);

    Double calcularGanancia(Run run);

    Integer getCantidadTurnosJugados(Run run);
}
