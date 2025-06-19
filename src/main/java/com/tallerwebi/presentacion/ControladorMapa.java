package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.Mar;
import com.tallerwebi.dominio.excepcion.MonedasInsuficientesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.tallerwebi.dominio.ServicioMapa;
import com.tallerwebi.dominio.ServicioJugador;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorMapa {

    /*conexion a servicios (logica de negocio)*/
    private ServicioMapa servicioMapa;
    private ServicioJugador servicioJugador;

    /*constructor las inyecciones*/
    @Autowired
    public ControladorMapa(ServicioMapa servicioMapa, ServicioJugador servicioJugador) {
        this.servicioMapa = servicioMapa;
        this.servicioJugador = servicioJugador;
    }

    /*redirecciones*/
    @RequestMapping("/mapa")
    public ModelAndView vistaMapa() {
        return new ModelAndView("vistaMapa");
    }

  @RequestMapping("/tienda")
    public ModelAndView irAVistaTienda(){
        return new ModelAndView("vistaTienda");
    }


    @RequestMapping("/logros")
    public ModelAndView irAVistaLogros(){
        return new ModelAndView("vistaLogros");
    }

    @RequestMapping("/marSeleccionado")
    public ModelAndView redireccionSegunSiEstaBloqueadoONo(@ModelAttribute Jugador jugador,@ModelAttribute Mar mar) {
        ModelMap mm = new ModelMap();
        Jugador jugadorActual = servicioJugador.buscarJugadorPorId(jugador.getId_jugador());
        if (jugadorActual == null){
            mm.put("JugadorError", "El jugador no existe");
            return new ModelAndView("login",mm);
        }
       // Jugador jugadorActual = servicioJugador.buscarJugador(jugador);//-> base de datos
         boolean estado = servicioMapa.obtenerElEstadoDelMarSegunELJugador(mar,jugadorActual);// -> base datos join usuario es
         if (estado){
             mm.put("marError", "El mar seleccionado esta bloqueado");
             return new ModelAndView("vistaMarBloqueado",mm);
         }

       // if(jugadorActual == null){
         //   mm.put("mensaje", "El jugador no existe");
          //  return new ModelAndView("login", mm);
        //}

       // if(marSeleccionado == null){
         //   mm.put("mensaje", "El mar seleccionado esta bloqueado");
           // return new ModelAndView("vistaMarbloqueado", mm);
       // }

        return new ModelAndView("vistaSeleccion");
    }



    @RequestMapping("/marSeleccionadoPrueba")
    public ModelAndView redireccionDeVistasDependiendoDelUsuario(String aliasJugador, Double monedasJuntadas) {
        ModelMap modelMap = new ModelMap();
        Mar marFalse = new Mar("mar sett",0.0,"pueba",false );
        Mar marTrue = new Mar("mar sett",0.0,"pueba",true );

        if (monedasJuntadas == 0.0 ) {
            modelMap.put("mensajeDeVistaError", "El Usuario no cuenta con Monedas");
            return new ModelAndView("vistaMapa", modelMap);
        }

        /*agregue 31/05 ->
        * hasta que no cree una clase me va a dar null xq no esta llamando a uno
        * asi que por ahora lo sett para hacer los test*/
        if (marFalse.getEstadoBloqueado().equals(false)) {
            modelMap.put("mensajeDeVistaError", "El mar se encuentra en estado BLOQUEADO");
            return new ModelAndView("vistaMapa", modelMap);
        }else if (marTrue.getEstadoBloqueado().equals(true)) {
            return new ModelAndView("vistaSeleccion", modelMap);
        }

       // agregue una exception -> try catch DUDA: esto deberia ir a vistaMapa o vistaMarBloqueado?
        try {
            servicioMapa.calcularSiSePuedeDesbloquearUnMar(aliasJugador, monedasJuntadas);
        }catch (MonedasInsuficientesException e) {
            modelMap.put("mensajeErrorMonedas", "El Usuario no tiene suficientes monedas para desbloquear el mar");
            return new ModelAndView("vistaMapa", modelMap);
        }


        return new ModelAndView("vistaSeleccion");
    }


}
