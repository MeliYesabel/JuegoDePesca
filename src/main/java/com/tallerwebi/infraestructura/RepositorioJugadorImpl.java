package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.Mar;
import com.tallerwebi.dominio.RepositorioJugador;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioJugadorImpl implements RepositorioJugador {

    /*conecta a la base de datos*/
    @Autowired
    private SessionFactory sessionFactory;

    /*querys */
    @Override
    public Jugador buscarjugadorPorId(Long idJugador) {
        var session = sessionFactory.getCurrentSession();
        return (Jugador) session.createCriteria(Jugador.class)
                .add(Restrictions.eq("id_jugador", idJugador))
                .uniqueResult(); /*si no encuentra nada me retorna NULL*/
    }
}
