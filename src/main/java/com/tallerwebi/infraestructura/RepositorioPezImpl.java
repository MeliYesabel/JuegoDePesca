package com.tallerwebi.infraestructura;
import com.tallerwebi.dominio.Pez;
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
}
