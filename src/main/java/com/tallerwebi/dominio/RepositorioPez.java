package com.tallerwebi.dominio;

import org.hibernate.SessionFactory;

import java.util.List;

public interface RepositorioPez {
    List<Pez> buscarPezPorMar(String nombreDelMar);
}
