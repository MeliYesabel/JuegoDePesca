package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.MonedasInsuficientesException;
import com.tallerwebi.dominio.ServicioTienda;
import com.tallerwebi.dominio.excepcion.Objeto;
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
    @BeforeEach
    public void init(){
        jugador = new Jugador();
         objeto = new Objeto(1,100.0);
        servicioTienda = mock(ServicioTienda.class);
        Integer idObjeto = (int) objeto.getIdObjeto();
        controladorTienda = new ControladorTienda(servicioTienda);
       session = mock(HttpSession.class);
        when(session.getAttribute("jugador")).thenReturn(jugador);
    }



    @Test
public void dadoQueCuandoComproUnObjetoYMeDevuelveTrueQueMeDevulevaLaVistaObjetoHtml(){

        when(servicioTienda.comprarObjeto(jugador,(int)objeto.getIdObjeto())).thenReturn(true);
        ModelAndView modelAndView = controladorTienda.comprarObjeto(session,1);
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("vistaObjeto.html"));


    }

    @Test
    public void dadoQueCuandoComproUnObjetoYMeDevuelveFalseQueMeDevuelvaLaVistaTiendaHtml(){//si falla la compra de un objeto vuelva a la tienda
        when(servicioTienda.comprarObjeto(jugador,objeto.getIdObjeto())).thenThrow(new MonedasInsuficientesException("No hay monedas"));
                //.thenReturn(false);
        ModelAndView modelAndView = controladorTienda.comprarObjeto(session,1);
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("vistaTienda.html"));// en realidad seria vista compra sin exito
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
