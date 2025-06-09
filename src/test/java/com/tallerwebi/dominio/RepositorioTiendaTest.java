package com.tallerwebi.dominio;

//import com.mysql.cj.xdevapi.SessionFactory;

import com.tallerwebi.infraestructura.RepositorioObjeto;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.hibernate.SessionFactory;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioTiendaTest {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    RepositorioObjeto repositorioObjeto;


    @Test
    public void puedoObtenerUnaListaDeObjetosPorLikeNombre() {
        Objeto objeto = new Objeto(100.0, "caña madera");
        Objeto objeto2 = new Objeto(1000.0, "caña metal");
        Objeto objeto3 = new Objeto(1200.0, "caña oro");
        Objeto objeto4 = new Objeto(1300.0, "cañita diamante");

    }


}
