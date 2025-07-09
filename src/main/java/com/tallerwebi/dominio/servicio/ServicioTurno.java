package com.tallerwebi.dominio.servicio;

import com.tallerwebi.dominio.entidad.Turno;
import com.tallerwebi.dominio.entidad.Pez;
import com.tallerwebi.dominio.entidad.Run;

import java.util.List;

public interface ServicioTurno {
    Turno crearUnTurno(Run run);
    Turno crearUnTurno();
    List<Pez> tomarTresPecesParaElTurno(Run run);
    Pez seleccionarUnPez(List<Pez> pecesGenerados, Integer posicionDelPez);
    List<Pez> obtenerTresPecesAleatorios();
    Pez pescarPezPorId(Long id);
    Boolean pesca(Pez pezSeleccionado);
}
