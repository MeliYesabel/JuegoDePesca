package com.tallerwebi.dominio.repositorio;

import com.tallerwebi.dominio.entidad.Pez;

import java.util.List;

public interface RepositorioPez {
    List<Pez> buscarPezPorMar(String nombreDelMar);

    List<Pez> obtenerTodosLosPeces();

    List<Pez> buscarPorIdMar(Long idMar);
}
