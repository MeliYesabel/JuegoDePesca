package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.repositorio.RepositorioUsuarioAuth;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioUsuarioAuthTest {
    @Autowired
    private SessionFactory sessionFactory; //creo una sesion para conectarme a la bd en memoria
    private RepositorioUsuarioAuth repositorioUsuarioAuth;

    @BeforeEach
    public void init(){
        this.repositorioUsuarioAuth = new RepositorioUsuarioAuthImpl(this.sessionFactory);
    }





}
