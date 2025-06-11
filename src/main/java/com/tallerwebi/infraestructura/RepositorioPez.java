package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Pez;

import java.util.List;

public interface RepositorioPez {
    List<Pez> buscarPezPorMar(String nombreDelMar);
}
