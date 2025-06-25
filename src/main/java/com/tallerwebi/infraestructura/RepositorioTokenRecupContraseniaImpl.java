package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.TokenRecuperacionContrasenia;
import com.tallerwebi.dominio.entidad.UsuarioAuth;
import com.tallerwebi.dominio.repositorio.RepositorioTokenRecupContrasenia;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioTokenRecupContraseniaImpl implements RepositorioTokenRecupContrasenia {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioTokenRecupContraseniaImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void eliminar(TokenRecuperacionContrasenia tokenRecibido) {
        sessionFactory.getCurrentSession().delete(tokenRecibido);

    }

    @Override
    public void guardar(TokenRecuperacionContrasenia tokenEntidad) {
        sessionFactory.getCurrentSession().save(tokenEntidad);
    }

    @Override
    public TokenRecuperacionContrasenia buscarPorToken(String token) {
        return (TokenRecuperacionContrasenia) sessionFactory.getCurrentSession().createCriteria(TokenRecuperacionContrasenia.class)
                .add(Restrictions.eq("token",token))
                .uniqueResult();

    }
}
