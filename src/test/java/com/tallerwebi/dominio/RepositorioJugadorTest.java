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
    Jugador jugador;
    Objeto objeto;

    @BeforeEach
    public void setUp() {
        repositorioJugador = new RepositorioJugadorImpl(sessionFactory);
         jugador = new Jugador();
         objeto = new Objeto(100.0, "Cania madera");
    }

    @Test
    public void queAlAgregarUnObjetoAlJugadorSeGuardeEnLaBaseDeDatos() {

        jugador.agregarObjeto(objeto);
       repositorioJugador.guardarJugador(jugador);
       List<Objeto> listaObjeto = repositorioJugador.obtenerListaDeObjetosDelJugador(jugador);

       assertEquals(listaObjeto.size(),1);
        //assertThat();
    }

    @Test
    public void dadoQueTengoUnJugadorQueSeGuardeCorrectamenteEnLaBaseDeDatos() {

        jugador.setNombre("Gonza");

        repositorioJugador.guardarJugador(jugador);

        Jugador encontrado = repositorioJugador.buscarJugador(jugador.getId());
        assertEquals(jugador, encontrado);
    }


}
