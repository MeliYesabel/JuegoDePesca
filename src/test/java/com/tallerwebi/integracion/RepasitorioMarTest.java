package com.tallerwebi.integracion;

import com.tallerwebi.dominio.Mar;
import com.tallerwebi.dominio.RepositorioMar;
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
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/*agregar a clase de tipo test + database : (siempre)*/
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})

public class RepasitorioMarTest {

    /*para que se una a la base de datos*/
    @Autowired
    private SessionFactory sessionFactory;

    /*repo de interfaz donde busca los metodos a usar */
    @Autowired
    private RepositorioMar repo;

    /*test ----------------------------------- */

    @Test
    @Transactional
    @Rollback
    public void queSePuedaTraerUnMarPorNombre() {
        givenTodosLosMaresAgregadosDeAUnoUsandoOtroMetodo();
        Mar marSolicitado = whenObtenerUnMarPorNombre("Poseidon");
        thenComprobarTestExitosoConMAR(marSolicitado);

    }

    private Mar whenObtenerUnMarPorNombre(String nombreMar) {
        return  repo.obtenerMarPorNombre(nombreMar);
    }

    private void thenComprobarTestExitosoConMAR(Mar marSolicitado) {
        assertThat(marSolicitado.getNombre(), equalTo("Poseidon"));
    }


    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerListaDeMaresBloqueados() {
        givenTodosLosMaresAgregadosDeAUnoUsandoOtroMetodo();
        List<Mar> maresBloqueados = whenObtenerListaDeMaresBloqueados();
        thenComprobarSiElTestFueExitosoConLista(maresBloqueados,6);
    }

    private List<Mar> whenObtenerListaDeMaresBloqueados() {
        return repo.obtenerListaDeMaresBloqueados();
    }


    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerLaListaDeMaresDesbloqueado() {
        givenTodosLosMaresAgregadosDeAUnoUsandoOtroMetodo();
        List<Mar> maresDesbloqueados = whenObtenerListaDeMaresDesbloqueados();
        thenComprobarSiElTestFueExitosoConLista(maresDesbloqueados,1);

    }

    private List<Mar> whenObtenerListaDeMaresDesbloqueados() {
        return repo.obtenerListaDeMaresDesbloqueados();
    }


    @Test
    @Transactional/*-> para que sepa que va a estar en la base de datos*/
    @Rollback /*-> para que todo_lo creado(instancias) solo sea es este test*/
    public void queSePuedaobtenerLaListaCompletaDeTodosLosMaresQueExtanEnElMapa(){
        givenTodosLosMaresAgregadosDeAUnoUsandoOtroMetodo();
        List<Mar> mares =  whenObtenerLaListaCompletaDeTodosLosMares();
        thenComprobarSiElTestFueExitosoConLista(mares,7);
    }

    private void givenTodosLosMaresAgregadosDeAUnoUsandoOtroMetodo(){
        /*String nombre, Double precio, String descripcion, Boolean estado*/
        givenAgregarMaresALista("Poseidon",0.0,"Mitologia Grega",false);
        givenAgregarMaresALista("Njoror",100.0,"Mitologia Nordica",true);
        givenAgregarMaresALista("Susanoo",150.0,"Mitologia Japonesa",true);
        givenAgregarMaresALista("Yemaya",200.0,"Mitologia Yoriba",true);
        givenAgregarMaresALista("Tlaloc",250.0,"Mitologia Azteca",true);
        givenAgregarMaresALista("Varuna",300.0,"Mitologia Hindu ",true);
        givenAgregarMaresALista("Nuwa",400.0,"Mitologia China",true);
    }

    private void givenAgregarMaresALista(String marNombre, Double precio, String descripcion, Boolean estadoBloqueado){
        Mar mar = new Mar(marNombre,precio,descripcion,estadoBloqueado);
        sessionFactory.getCurrentSession().save(mar);/*creo que esto debe estar en la claseImplement*/
    }

    private List<Mar> whenObtenerLaListaCompletaDeTodosLosMares() {
        return repo.obtenerLaListaCompletaDeTodosLosMares();
    }

    private void thenComprobarSiElTestFueExitosoConLista(List<Mar> mares, Integer cant_total) {
        assertThat(mares.size(),equalTo(cant_total));
    }



}
