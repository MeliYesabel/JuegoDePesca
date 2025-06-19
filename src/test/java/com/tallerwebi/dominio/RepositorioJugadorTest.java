package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.RepositorioJugadorImpl;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = HibernateTestConfig.class) /*{SpringWebTestConfig.class,HibernateTestConfig.class }*/
@Transactional
@Rollback
public class RepositorioJugadorTest {

    @Autowired
    SessionFactory sessionFactory;

    RepositorioJugadorImpl repositorioJugador;

    @BeforeEach
    public void setUp() {
        repositorioJugador = new RepositorioJugadorImpl(sessionFactory);
    }

    @Test
    public void queAlAgregarUnObjetoAlJugadorSeGuardeEnLaBaseDeDatos() {
        Objeto objeto = new Objeto();
        Jugador jugador = new Jugador();
        jugador.agregarObjeto(objeto);
       repositorioJugador.guardarJugador(jugador);
       List<Objeto> listaObjeto = repositorioJugador.obtenerListaDeObjetosDelJugador(jugador);

       assertEquals(listaObjeto.size(),1);
        //assertThat();
    }

}
