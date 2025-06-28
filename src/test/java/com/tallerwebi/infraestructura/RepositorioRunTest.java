package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.repositorio.RepositorioRun;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioRunTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    RepositorioRun repositorioRun;
/* PARA SOLUCIONAR CUANDO ESTEMOS TODOS JUNTOS
    @Test
    @Transactional
    @Rollback
    public void obtenerResumenDePartida() {
        givenSeTerminoUnaPartida();
        *Resumen resumen = whenCalculoResumenDeUnaPartida();
        *thenObtengoResumen(resumen);
    }

    private void givenSeTerminoUnaPartida() {
    }

    private Resumen whenCalculoResumenDeUnaPartida() {
        return null;
    }
    private void thenObtengoResumen(Resumen resumen) {
    }*/
}
