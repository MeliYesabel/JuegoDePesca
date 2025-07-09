package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.JugadorMar;
import com.tallerwebi.dominio.entidad.Mar;
import com.tallerwebi.dominio.repositorio.RepositorioMar;
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
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNull;

/*agregar a clase de tipo test + database : (siempre)*/
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})

public class RepasitorioMarTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private RepositorioMar repo;

    @Test
    @Transactional
    @Rollback
    public void queMeDevuelvaNullSiElJugadorMarBuscadoNOFueEncontrado() {
        Mar mar = new Mar("Poseidon", 0.0, "Mitologia Griega", true);
        mar.setId_mar(1L);
        sessionFactory.getCurrentSession().save(mar);
        Jugador jugador = new Jugador("Anahi","anis",30.0,1);
        sessionFactory.getCurrentSession().save(jugador);
        JugadorMar jugadorMar= new JugadorMar(jugador,mar,true);
        sessionFactory.getCurrentSession().save(jugadorMar);

        JugadorMar obtener = repo.obtenerElJugadorMarBuscado(null,jugador);

        assertNull(obtener);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerElJugadorMarBuscadoSiLoEncuantraQueMeDevuelvaElObjeto() {
        Mar mar = new Mar("Poseidon", 0.0, "Mitologia Griega", true);
        mar.setId_mar(1L);
        sessionFactory.getCurrentSession().save(mar);
        Jugador jugador = new Jugador("Anahi","anis",30.0,1);
        sessionFactory.getCurrentSession().save(jugador);
        JugadorMar jugadorMar= new JugadorMar(jugador,mar,true);
        sessionFactory.getCurrentSession().save(jugadorMar);

        JugadorMar obtener = repo.obtenerElJugadorMarBuscado(mar,jugador);

        assertThat(obtener, equalTo(jugadorMar));
    }


    @Test
    @Transactional
    @Rollback
    public void obtenerElEstadoDelMarSolicitadoQueMeDeVerdeSiEstaBloqueadosegunElJugador() {

        // no puedo hacer un given ya que para comprobar de la base de datos
        //esos objetos los necesito de aca

        Mar mar = new Mar("Poseidon", 0.0, "Mitologia Griega", true);
        mar.setId_mar(1L);
        sessionFactory.getCurrentSession().save(mar);
        Jugador jugador = new Jugador("Anahi","anis",30.0,1);
        sessionFactory.getCurrentSession().save(jugador);
        JugadorMar jugadorMar= new JugadorMar(jugador,mar,true);
        sessionFactory.getCurrentSession().save(jugadorMar);

        boolean estado = repo.obtenerElEstadoMarDelJugador(mar,jugador);
        assertThat(estado,equalTo(true));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerUnMarSiEstaBloqueadoElPrecioParaDesbloqueralo(){
        givenTodosLosMaresAgregadosDeAUnoUsandoOtroMetodo();

        Mar mar = whenObtenerUnMarSiEstbloqueadoElPrecio("Njoror");
        thenQueMeDevuelvaLacantDeMonedas(mar,100.0);
    }

    private Mar whenObtenerUnMarSiEstbloqueadoElPrecio(String marBloqueado) {
        return repo.obtenerElPrecioDeUnMarBloqueado(marBloqueado);
    }

    private void thenQueMeDevuelvaLacantDeMonedas(Mar mar, Double precio ) {
        assertThat(mar.getPrecio(), equalTo(precio));
    }

    @Test
    @Transactional
    @Rollback
    public void queMeDeVerdeSiElMarBuscadoEstaDesbloqueado(){
        givenTodosLosMaresAgregadosDeAUnoUsandoOtroMetodo();
        Mar mar = whenObtenerUnMarPorNombreSiEstaDesbloqueado("Poseidon");
        thenComprobarTestExitosoConMAREstadoBloqueado(mar,false);
    }

    private Mar whenObtenerUnMarPorNombreSiEstaDesbloqueado(String nombre) {
        return repo.obtenerMarPorNombreSiEsteEstaDesbloqeuado(nombre);
    }

    private void thenComprobarTestExitosoConMAREstadoBloqueado(Mar mar, Boolean estadoBloqueado) {
        assertThat(mar.getEstadoBloqueado(), equalTo(estadoBloqueado));
    }

   /* @Test  -> practicar con excepcion
    @Transactional
    @Rollback
    public void queNOSePuedaTraerUnMarSiElNivelEstaBoqueado(){

    }*/

    @Test
    @Transactional
    @Rollback
    public void queSePuedaTraerUnMarPorNombre() {
        givenTodosLosMaresAgregadosDeAUnoUsandoOtroMetodo();
        Mar marSolicitado = whenObtenerUnMarPorNombre("Poseidon");
        thenComprobarTestExitosoConMAR(marSolicitado,"Poseidon");

    }

    private Mar whenObtenerUnMarPorNombre(String nombreMar) {
        return  repo.obtenerMarPorNombre(nombreMar);
    }

    private void thenComprobarTestExitosoConMAR(Mar marSolicitado,String nombreMarABuscar) {
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
        Mar mar = new Mar("Poseidon",0.0,"Mitologia Griega",false);
        mar.setId_mar(1L);
        sessionFactory.getCurrentSession().save(mar);

        Mar mar2 = new Mar("Njoror",100.0,"Mitologia Nordica",true);
        mar2.setId_mar(2L);
        sessionFactory.getCurrentSession().save(mar2);

        Mar mar3 = new Mar("Susanoo",150.0,"Mitologia Japonesa",true);
        mar3.setId_mar(3L);
        sessionFactory.getCurrentSession().save(mar3);

        Mar mar4 = new Mar("Yemaya",200.0,"Mitologia Yoriba",true);
        mar4.setId_mar(4L);
        sessionFactory.getCurrentSession().save(mar4);

        Mar mar5 = new Mar("Tlaloc",250.0,"Mitologia Azteca",true);
        mar5.setId_mar(5L);
        sessionFactory.getCurrentSession().save(mar5);

        Mar mar6 = new Mar("Varuna",300.0,"Mitologia Hindu ",true);
        mar6.setId_mar(6L);
        sessionFactory.getCurrentSession().save(mar6);

        Mar mar7 = new Mar("Nuwa",400.0,"Mitologia China",true);
        mar7.setId_mar(7L);
        sessionFactory.getCurrentSession().save(mar7);
    }


    private List<Mar> whenObtenerLaListaCompletaDeTodosLosMares() {
        return repo.obtenerLaListaCompletaDeTodosLosMares();
    }

    private void thenComprobarSiElTestFueExitosoConLista(List<Mar> mares, Integer cant_total) {
        assertThat(mares.size(),equalTo(cant_total));
    }



}
