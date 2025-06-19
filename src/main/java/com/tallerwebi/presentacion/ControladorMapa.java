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

         boolean estado = servicioMapa.obtenerElEstadoDelMarSegunELJugador(mar,jugadorActual);// -> base datos join usuario es
         if (estado){
             mm.put("marError", "El mar seleccionado esta bloqueado");
             return new ModelAndView("vistaMarBloqueado",mm);
         }


        return new ModelAndView("vistaSeleccion");
    }


}
