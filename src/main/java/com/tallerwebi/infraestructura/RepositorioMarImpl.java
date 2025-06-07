package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Mar;
import com.tallerwebi.dominio.RepositorioMar;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sun.tools.attach.VirtualMachine.list;

/*no olvidar de poner el repo*/
@Repository
public class RepositorioMarImpl implements RepositorioMar {

    /*para unir a la base de datos se necesita esto */
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Mar> obtenerLaListaCompletaDeTodosLosMares() {
        /*mas esto para que se realice completamente con la base de datos*/
        var session = sessionFactory.getCurrentSession();
       return session.createCriteria(Mar.class).list();
    }

    /*
    * select *
    * from mar
    * where estadoBloqueado = 'false'*/

    @Override
    public List<Mar> obtenerListaDeMaresDesbloqueados() {
        var session = sessionFactory.getCurrentSession();
        return session.createCriteria(Mar.class)
                .add(Restrictions.eq("estadoBloqueado",false))
                .list();
    }
}
