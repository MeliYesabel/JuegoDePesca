package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.UsuarioAuth;
import com.tallerwebi.dominio.repositorio.RepositorioUsuarioAuth;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioUsuarioAuthImpl implements RepositorioUsuarioAuth {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioUsuarioAuthImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UsuarioAuth buscarPorMail(String email) {
        return (UsuarioAuth) sessionFactory.getCurrentSession().createCriteria(UsuarioAuth.class)
                .add(Restrictions.eq("email",email))
                .uniqueResult();
    }

    @Override
    public void guardar(UsuarioAuth nuevoUsuario) {
        sessionFactory.getCurrentSession().save(nuevoUsuario);
    }
}
