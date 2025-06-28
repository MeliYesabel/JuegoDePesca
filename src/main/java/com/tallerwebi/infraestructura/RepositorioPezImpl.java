package com.tallerwebi.infraestructura;
import com.tallerwebi.dominio.entidad.Pez;
import com.tallerwebi.dominio.repositorio.RepositorioPez;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioPezImpl implements RepositorioPez {

   @Autowired
   SessionFactory sessionFactory;

    @Override
    public List<Pez> buscarPezPorMar(String nombreDelMar) {
        var session = sessionFactory.getCurrentSession();
        return session.createCriteria(Pez.class)
                .createAlias("mar", "m")
                .add(Restrictions.eq("m.nombre", nombreDelMar))
                .list();
    }

    @Override
    public List<Pez> obtenerTodosLosPeces() {
        var session = sessionFactory.getCurrentSession();
        return session.createCriteria(Pez.class).list();
    }

    @Override
    public List<Pez> buscarPorIdMar(Long idMar) {
        var session = sessionFactory.getCurrentSession();
        return session.createCriteria(Pez.class)
                .createAlias("mar", "m")
                .add(Restrictions.eq("m.id_mar", idMar))
                .list();
    }
}
