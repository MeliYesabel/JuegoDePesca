package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Pez;
import com.tallerwebi.dominio.entidad.Turno;
import com.tallerwebi.infraestructura.RepositorioTurnoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class RepositorioTurnoImplTest {

    private RepositorioTurnoImpl repositorio;
    private EntityManager em;
    private SessionFactory sessionFactory;
    private Session session;

    @BeforeEach
    public void setUp() {
        repositorio = new RepositorioTurnoImpl();

        em = mock(EntityManager.class);
        sessionFactory = mock(SessionFactory.class);
        session = mock(Session.class);

        repositorio.sessionFactory = sessionFactory;


        try {
            var field = RepositorioTurnoImpl.class.getDeclaredField("em");
            field.setAccessible(true);
            field.set(repositorio, em);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testObtenerTodosLosPeces_devuelveListaDePeces() {

        TypedQuery<Pez> queryMock = mock(TypedQuery.class);
        List<Pez> pecesEsperados = Arrays.asList(new Pez(), new Pez());

        when(em.createQuery("FROM Pez", Pez.class)).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(pecesEsperados);

        List<Pez> resultado = repositorio.obtenerTodosLosPeces();

        assertEquals(2, resultado.size());
        verify(em).createQuery("FROM Pez", Pez.class);
        verify(queryMock).getResultList();
    }

    @Test
    public void testGuardarTurno_llamaSaveConTurno() {
        Turno turno = new Turno();

        when(sessionFactory.getCurrentSession()).thenReturn(session);

        repositorio.guardarTurno(turno);

        verify(sessionFactory).getCurrentSession();
        verify(session).save(turno);
    }
}
