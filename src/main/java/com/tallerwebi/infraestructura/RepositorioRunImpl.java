package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.repositorio.RepositorioRun;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioRunImpl implements RepositorioRun {

    @Autowired
    SessionFactory sessionFactory;
}
