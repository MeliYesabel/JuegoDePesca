package com.tallerwebi.infraestructura;

//import com.mysql.cj.xdevapi.SessionFactory;

import com.tallerwebi.dominio.Objeto;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = HibernateTestConfig.class) /*{SpringWebTestConfig.class,HibernateTestConfig.class }*/
@Transactional
@Rollback
public class RepositorioObjetoTest {

    @Autowired
    SessionFactory sessionFactory;


    RepositorioObjetoImpl repositorioObjeto;
    Objeto objeto;
    Objeto objeto2;

    @BeforeEach
    public void setUp() {
        objeto =  new Objeto(100.0, "Cania madera");
        objeto2 = new Objeto(150.0, "Cania metal");
        repositorioObjeto = new RepositorioObjetoImpl(sessionFactory);
    }


    @Test
    public void dadoQueTengoUnObjetoQueSeGuardeCorrectamenteEnLaBaseDeDatos() {

        repositorioObjeto.guardarObjeto(objeto);

        Objeto objetoBuscado = repositorioObjeto.buscarObjeto(objeto.getIdObjeto());
        assertEquals(objeto, objetoBuscado);
    }

    @Test
    public void dadoQueTendoUnaListaDeObjetosQueMeTraigaTodosLosObjetos() {
        repositorioObjeto.guardarObjeto(objeto);
        repositorioObjeto.guardarObjeto(objeto2);

        List<Objeto> lista = repositorioObjeto.obtenerTodosLosObjetos();
        assertEquals(2, lista.size());
    }



   /* @Test
    public void puedoObtenerUnaListaDeObjetosPorLikeNombre() {
        Objeto objeto = new Objeto(100.0, "caña madera");
        Objeto objeto2 = new Objeto(1000.0, "caña metal");
        Objeto objeto3 = new Objeto(1200.0, "caña oro");
        Objeto objeto4 = new Objeto(1300.0, "cañita diamante");

        sessionFactory.getCurrentSession().save(objeto);
        sessionFactory.getCurrentSession().save(objeto2);
        sessionFactory.getCurrentSession().save(objeto3);
        sessionFactory.getCurrentSession().save(objeto4);

      List<Objeto> objetosPorNombreLike =   repositorioObjeto.buscarListaObjetosPorNombreLike("caña madera");

        assertThat(objetosPorNombreLike.size(),equalTo(1));
    }*/




}
