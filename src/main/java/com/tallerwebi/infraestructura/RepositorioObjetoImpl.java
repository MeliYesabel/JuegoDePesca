package com.tallerwebi.infraestructura;

/*import com.mysql.cj.xdevapi.SessionFactory;*/
import com.tallerwebi.dominio.Objeto;
import com.tallerwebi.dominio.RepositorioObjeto;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory; // ✅

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class RepositorioObjetoImpl implements RepositorioObjeto {

  // @Autowired
   SessionFactory sessionFactory;

    public RepositorioObjetoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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
    public List<Objeto> buscarListaObjetosPorNombreLike(String nombre) {
        var session = sessionFactory.getCurrentSession();
        return session.createCriteria(Objeto.class).add(Restrictions.like("nombre",nombre +"%")).list();
    }

    @Override
    public List<Objeto> obtenerTodosLosObjetos(){
        var session = sessionFactory.getCurrentSession();
        return session.createCriteria(Objeto.class).list();
    }
}
