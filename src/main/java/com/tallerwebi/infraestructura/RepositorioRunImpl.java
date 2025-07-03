package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Objeto;
import com.tallerwebi.dominio.entidad.Pez;
import com.tallerwebi.dominio.entidad.Run;
import com.tallerwebi.dominio.repositorio.RepositorioRun;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioRunImpl implements RepositorioRun {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void crearUnaRun(Run run) {
        sessionFactory.getCurrentSession().save(run);
    }

    @Override
    public Run obtenerRunPorId(Long idRun) {
        var session = sessionFactory.getCurrentSession();
        return session.get(Run.class, idRun);
    }

}
