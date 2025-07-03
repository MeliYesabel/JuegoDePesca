package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Objeto;
import com.tallerwebi.dominio.repositorio.RepositorioJugador;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

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
    // sessionFactory.getCurrentSession().save(jugador);
        sessionFactory.getCurrentSession().saveOrUpdate(jugador);

    }

    @Override
    public List<Objeto> obtenerListaDeObjetosDelJugador(Jugador jugador) {
        var session = sessionFactory.getCurrentSession();
        return session.get(Jugador.class,jugador.getId()).getObjetosComprados();
    }

    @Override
    public Jugador buscarjugadorPorId(Long idJugador) {
        var session = sessionFactory.getCurrentSession();
        return (Jugador) session.createCriteria(Jugador.class)
                .add(Restrictions.eq("id", idJugador))
                .uniqueResult(); /*si no encuentra nada me retorna NULL*/
    }

    @Override
    public void actualizarDatosDeJugadorYaExistente(Jugador jugador) {
        sessionFactory.getCurrentSession().merge(jugador);
    }
}
