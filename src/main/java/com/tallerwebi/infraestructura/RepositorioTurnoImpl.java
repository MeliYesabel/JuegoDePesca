package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Pez;
import com.tallerwebi.dominio.entidad.Run;
import com.tallerwebi.dominio.entidad.Turno;
import com.tallerwebi.dominio.repositorio.RepositorioTurno;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class RepositorioTurnoImpl implements RepositorioTurno {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Pez> obtenerTodosLosPeces() {
        return em.createQuery("FROM Pez", Pez.class).getResultList();
    }

    @Override
    public void guardarTurno(Turno turno) {
        sessionFactory.getCurrentSession().save(turno);
    }
}
