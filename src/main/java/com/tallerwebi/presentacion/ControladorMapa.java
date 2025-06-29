package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Mar;
import com.tallerwebi.presentacion.dto.UsuarioSesionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.tallerwebi.dominio.servicio.ServicioMapa;
import com.tallerwebi.dominio.servicio.ServicioJugador;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
        modelMap.addAttribute("listaMar", listaMar);

        return new ModelAndView("vistaMapa",modelMap);
    }

    @RequestMapping("/logros")
    public ModelAndView irAVistaLogros(){
        return new ModelAndView("vistaLogros");
    }

    @RequestMapping("/marSeleccionado/{nombreMar}")
    public ModelAndView redireccionSegunSiEstaBloqueadoONo(HttpSession session, @PathVariable ("nombreMar") String nombreMar) {
        ModelMap mm = new ModelMap();

        /*utilizo una session devuelve un long */
        UsuarioSesionDto usuarioSesion = (UsuarioSesionDto) session.getAttribute("usuarioLogueado");
        Jugador jugador = servicioJugador.buscarJugadorPorId(usuarioSesion.getId());

        /*HTTP ERROR 500 javax.servlet.ServletException: deberia buscar el jugador si es null o una exception*/

        Mar mar = servicioMapa.obtenerUnMarPorNombre(nombreMar);
        if (mar == null) {
            mm.put("marError", "No existe ese mar");
            return new ModelAndView("vistaMapa",mm);
        }

        boolean estado = servicioMapa.obtenerElEstadoDelMarSegunELJugador(mar,jugador);// -> base datos join usuario es
        if (estado){
             mm.put("marError", "El mar seleccionado esta bloqueado");
             return new ModelAndView("vistaMarBloqueado",mm);
         }

        mm.put("mar", mar);

        return new ModelAndView("vistaSeleccion",mm);
    }


}
