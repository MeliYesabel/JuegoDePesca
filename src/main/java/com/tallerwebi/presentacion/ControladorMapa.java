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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    public ModelAndView irAVistaMapa() {
        ModelMap modelMap = new ModelMap();
        List<Mar> listaMar = servicioMapa.obtenerTodaListaDeMares();
        if (listaMar.isEmpty()) {
            modelMap.put("mensaje", "No se puede obtener la lista de mars");
            return new ModelAndView("login",modelMap);
        }
        modelMap.addAttribute("listaMar", listaMar);

        return new ModelAndView("vistaMapa",modelMap);
    }

  @RequestMapping("/tienda")
    public ModelAndView irAVistaTienda(){
        return new ModelAndView("vistaTienda");
    }


    @RequestMapping("/logros")
    public ModelAndView irAVistaLogros(){
        return new ModelAndView("vistaLogros");
    }

    @RequestMapping("/marSeleccionado/{nombreMar}")
    public ModelAndView redireccionSegunSiEstaBloqueadoONo(HttpSession session, @PathVariable ("nombreMar") String nombreMar) {
        ModelMap mm = new ModelMap();

        /*utilizo una session*/
        Jugador jugadorActual = (Jugador) session.getAttribute("jugador");

        /*if (jugadorActual == null){
            // por como esta la logica de la prof si quiero usar ellogin deberia
            // tener mm.put("datosLogin",new DatosLogin());
            mm.put("JugadorError", "El jugador no existe");
            mm.put("datosLogin",new DatosLogin());
            return new ModelAndView("vistaMapa",mm);
        }*/

        Mar mar = servicioMapa.obtenerUnMarPorNombre(nombreMar);
        boolean estado = servicioMapa.obtenerElEstadoDelMarSegunELJugador(mar,jugadorActual);// -> base datos join usuario es
         if (estado){
             mm.put("marError", "El mar seleccionado esta bloqueado");
             return new ModelAndView("vistaMarBloqueado",mm);
         }

        mm.put("mar", mar);

        return new ModelAndView("vistaSeleccion",mm);
    }


}
