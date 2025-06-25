package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class RepositorioTurnoImpl implements RepositorioTurno {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Pez> obtenerTodosLosPeces() {
        return em.createQuery("FROM Pez", Pez.class).getResultList();
    }
}
