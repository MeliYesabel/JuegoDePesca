package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.JugadorMar;
import com.tallerwebi.dominio.entidad.Mar;
import com.tallerwebi.dominio.repositorio.RepositorioMar;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Mar> obtenerListaDeMaresBloqueados() {
        var session = sessionFactory.getCurrentSession();
        return session.createCriteria(Mar.class)
                .add(Restrictions.eq("estadoBloqueado",true))
                .list();
    }


    /* select *
    * from mar
    * where nombre = 'nombreMar'*/

    @Override
    public Mar obtenerMarPorNombre(String nombreMar) {
        var session = sessionFactory.getCurrentSession();
        return (Mar)session.createCriteria(Mar.class)
                .add(Restrictions.eq("nombre", nombreMar))
                .uniqueResult();
    }

    /*select *
    * from mar
    * where nombre = 'nombre' && estadoBloqueado = false*/
    @Override
    public Mar obtenerMarPorNombreSiEsteEstaDesbloqeuado(String nombre) {
        var session = sessionFactory.getCurrentSession();
        return (Mar)session.createCriteria(Mar.class)
                .add(Restrictions.eq("nombre", nombre))
                .add(Restrictions.eq("estadoBloqueado",false))
                .uniqueResult();
    }

    /*select mar.precio
    * from mar
    * where nombre = 'nombre' && estadoBloqueado = true */
    @Override
    public Mar obtenerElPrecioDeUnMarBloqueado(String marBloqueado) {
        var session = sessionFactory.getCurrentSession();
        return (Mar) session.createCriteria(Mar.class)
                .add(Restrictions.eq("nombre", marBloqueado))
                .add(Restrictions.eq("estadoBloqueado",true))
                .uniqueResult();
    }

    @Override
    public boolean obtenerElEstadoMarDelJugador(Mar mar, Jugador jugadorActual) {
        var session = sessionFactory.getCurrentSession();
        JugadorMar jm = (JugadorMar) session.createCriteria(JugadorMar.class)
                .add(Restrictions.eq("mar", mar))
                .add(Restrictions.eq("jugador", jugadorActual))
                .uniqueResult();

        return jm!=null && jm.getEstadoBloqueado();
    }

    @Override
    public Mar obtenerMarPorId(Long idMar) {
        var session = sessionFactory.getCurrentSession();
        return (Mar)session.createCriteria(Mar.class)
                .add(Restrictions.eq("id_mar", idMar))
                .uniqueResult();
    }
}
