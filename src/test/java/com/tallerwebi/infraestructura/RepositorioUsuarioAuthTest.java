package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.entidad.UsuarioAuth;
import com.tallerwebi.dominio.repositorio.RepositorioUsuarioAuth;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.hibernate.Session;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioUsuarioAuthTest {
    @Autowired
    private SessionFactory sessionFactory; //creo una sesion para conectarme a la bd en memoria
    private RepositorioUsuarioAuth repositorioUsuarioAuth;
    private Session session;

    @BeforeEach
    public void init(){
        this.repositorioUsuarioAuth = new RepositorioUsuarioAuthImpl(this.sessionFactory);
        this.session = mock(Session.class);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaGuardarUnUsuarioEnLaBBDD(){
        qivenQueHayUnUsuarioGuardado();
        UsuarioAuth obtenido = whenSeConsultaUsuarioEnLaDB();
        thenSeGuardoExitosamente(obtenido);
    }

    private void qivenQueHayUnUsuarioGuardado() {
        Jugador jugador = new Jugador();
        jugador.setEmail("jesi@mail.com");
        jugador.setPassword("hash123");

        repositorioUsuarioAuth.guardar(jugador);
    }

    private UsuarioAuth whenSeConsultaUsuarioEnLaDB() {

        UsuarioAuth recuperado = repositorioUsuarioAuth.buscarPorMail("jesi@mail.com");

        return recuperado;
    }

    private void thenSeGuardoExitosamente(UsuarioAuth recuperado) {
        assertNotNull(recuperado);
        assertEquals("jesi@mail.com", recuperado.getEmail());
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaBuscarUnUsuarioPorSuMail(){

    }








}
