package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.excepcion.MonedasInsuficientesException;
import com.tallerwebi.dominio.ServicioTienda;
import com.tallerwebi.dominio.Objeto;
import com.tallerwebi.dominio.RepositorioObjeto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorTiendaTest {

    private Jugador jugador;
    private Objeto objeto;
    private ServicioTienda servicioTienda;
    private ControladorTienda controladorTienda;
    private  HttpSession session;
    private RepositorioObjeto repositorioObjeto;
    @BeforeEach
    public void init(){
        repositorioObjeto = mock(RepositorioObjeto.class);
        jugador = new Jugador();
         objeto = new Objeto(100.0,"caña metal");
         objeto.setIdObjeto(1L);
        servicioTienda = mock(ServicioTienda.class);
        Long idObjeto = objeto.getIdObjeto();
        controladorTienda = new ControladorTienda(servicioTienda,repositorioObjeto);
       session = mock(HttpSession.class);
        when(session.getAttribute("jugador")).thenReturn(jugador);
    }



    @Test
public void dadoQueCuandoComproUnObjetoYMeDevuelveTrueQueMeDevulevaLaVistaObjetoHtml(){

        when(servicioTienda.comprarObjeto(jugador,objeto.getIdObjeto())).thenReturn(true); //para que sirve esta linea si la saco y me corre igual el test?
        ModelAndView modelAndView = controladorTienda.comprarObjeto(session,1L);
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("vistaObjeto.html"));


    }


    @Test
    public void dadoQueCuandoComproUnObjetoYMeDevuelveFalseQueMeDevuelvaLaVistaCompraSinExitoHtml(){//si falla la compra de un objeto vuelva a la tienda
        when(servicioTienda.comprarObjeto(jugador,objeto.getIdObjeto())).thenThrow(new MonedasInsuficientesException("No hay monedas"));
                //.thenReturn(false);
        ModelAndView modelAndView = controladorTienda.comprarObjeto(session,1L);
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("vistaCompraSinExito.html"));// en realidad seria vista compra sin exito
    }
 /*  @Test
    public void queElUsuarioPuedaComprarMonedas() {

    }




    @Test
   public void queElUsuarioPuedaComprarUnObjeto(){
        givenNoExisteUsuario();
        ModelAndView mod = whenComprarObjeto(jugador,idObjeto);
        thenObjetoCompradoConExito(mod);

    }

    private void thenObjetoCompradoConExito() {
    }

    private ModelAndView whenComprarObjeto(Jugador jugador,Integer idObjeto) {
        ControladorTienda controladorTienda = new ControladorTienda(servicioTienda);
        ModelAndView mav = controladorTienda.comprarObjeto(jugador,idObjeto);
        return mav;


    }

    private void givenNoExisteUsuario() {
    }*/
}
