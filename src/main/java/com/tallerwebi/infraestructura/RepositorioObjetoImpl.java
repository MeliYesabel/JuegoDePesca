package com.tallerwebi.infraestructura;

/*import com.mysql.cj.xdevapi.SessionFactory;*/
import com.tallerwebi.dominio.Objeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory; // ✅

import java.util.List;


@Repository
public class RepositorioObjetoImpl implements RepositorioObjeto {

   @Autowired
   SessionFactory sessionFactory;

    @Override
    public Objeto buscarObjeto(int id) {
        var session = sessionFactory.getCurrentSession();
        return session.get(Objeto.class, id);
    }

    @Override
    public void guardarObjeto(Objeto objeto) {
        sessionFactory.getCurrentSession().save(objeto);
    }

    @Override
    public List<Objeto> buscarListaObjetosPorNombreLike() {
        return List.of();
    }
}
