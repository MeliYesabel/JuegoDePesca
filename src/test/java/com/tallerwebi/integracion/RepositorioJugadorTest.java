package com.tallerwebi.integracion;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.RepositorioJugador;
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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})

public class RepositorioJugadorTest {

    @Autowired
    private SessionFactory sessionFactory;/*para que se una a la base de datos*/

    @Autowired
    private RepositorioJugador repo;

    /*test ------------------------------ */

    @Test
    @Transactional
    @Rollback
    public void queAlBuscarUnJugadorMeDevuelveUnJugador() {
        Jugador creado = givenAgregarUnJugador("Anahi","anis",30.0,1);
        Jugador jugador = whenObtenerUnJugadorPorId(creado.getId());
        assertThat(jugador.getId(), equalTo(creado.getId()));

    }

    private Jugador whenObtenerUnJugadorPorId(Long id) {
        return (Jugador) repo.buscarjugadorPorId(id);
    }

    /*aunque ya tenga esto en la base de datos igual debo tenerlo ya que este test es unitaria*/
    private Jugador givenAgregarUnJugador(String nombre, String alias, Double monedas, Integer carnadas) {
        Jugador j = new Jugador(nombre,alias,monedas,carnadas);
        sessionFactory.getCurrentSession().save(j);
        return j;
    }

}
