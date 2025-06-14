package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.RepositorioJugador;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class RepositorioJugadorImpl implements RepositorioJugador {

    SessionFactory sessionFactory;

    public RepositorioJugadorImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Jugador buscarJugador(Long idJugador) {
        var session = sessionFactory.getCurrentSession();
      return   session.get(Jugador.class, idJugador);
    }

    @Override
    public void guardarJugador(Jugador jugador) {
     sessionFactory.getCurrentSession().save(jugador);
    }
}
