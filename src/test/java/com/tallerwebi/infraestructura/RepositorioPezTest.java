package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Mar;
import com.tallerwebi.dominio.Pez;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioPezTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    RepositorioPez repositorioPez;

    @Test
    @Transactional
    @Rollback
    public void puedoObtenerPecesDelMarNordico(){
    givenObtengoUnaListaDePecesConMarSeteados();
    List<Pez> pecesDelMarNoridico = whenBuscoPecesPorMar("Nordico");
    thenEncuentroPeces(pecesDelMarNoridico);
    }

    private void givenObtengoUnaListaDePecesConMarSeteados() {
        Mar nordico = new Mar();
        nordico.setNombre("Nordico");
        sessionFactory.getCurrentSession().save(nordico);
        Mar griego = new Mar();
        griego.setNombre("Griego");
        sessionFactory.getCurrentSession().save(griego);
        Mar japones = new Mar();
        japones.setNombre("Japones");
        sessionFactory.getCurrentSession().save(japones);
        Pez pez1 = new Pez();
        pez1.setMar(nordico);
        sessionFactory.getCurrentSession().save(pez1);
        Pez pez2 = new Pez();
        pez2.setMar(griego);
        sessionFactory.getCurrentSession().save(pez2);
        Pez pez3 = new Pez();
        pez3.setMar(nordico);
        sessionFactory.getCurrentSession().save(pez3);
        Pez pez4 = new Pez();
        pez4.setMar(griego);
        sessionFactory.getCurrentSession().save(pez4);
        Pez pez5 = new Pez();
        pez5.setMar(japones);
        sessionFactory.getCurrentSession().save(pez5);
    }
    private List<Pez> whenBuscoPecesPorMar(String nombre) {
        return repositorioPez.buscarPezPorMar(nombre);
    }
    private void thenEncuentroPeces(List<Pez> pecesDelMarNordico) {
        assertEquals( 2, pecesDelMarNordico.size());
    }




}
