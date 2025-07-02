package com.tallerwebi.dominio.repositorio;

import com.tallerwebi.dominio.entidad.Pez;
import com.tallerwebi.dominio.entidad.Turno;

import java.util.List;

public interface RepositorioTurno {
    List<Pez> obtenerTodosLosPeces();
    void guardarTurno(Turno turno);
}
