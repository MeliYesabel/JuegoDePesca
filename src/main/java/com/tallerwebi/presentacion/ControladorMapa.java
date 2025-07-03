package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Mar;
import com.tallerwebi.dominio.excepcion.NoSePuedodesbloquearElMarException;
import com.tallerwebi.presentacion.dto.UsuarioSesionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.tallerwebi.dominio.servicio.ServicioMapa;
import com.tallerwebi.dominio.servicio.ServicioJugador;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    /*implementar la cantidad de monedas que tiene eljugador */

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
            return new ModelAndView("redirect:/mapa",mm);
        }

        boolean estado = servicioMapa.obtenerElEstadoDelMarSegunELJugador(mar,jugador);// -> base datos join usuario es
        if (estado){
             mm.put("marError", "El mar seleccionado esta bloqueado");
             mm.put("mar",mar);
             return new ModelAndView("vistaMarBloqueado",mm);
         }

        // si pasa_todo guardo el mar para que se pueda mostar sus datos por pantalla
        mm.put("mar", mar);

        return new ModelAndView("vistaSeleccion",mm);
    }

    @PostMapping("/marBloqueado/{nombreMar}")
    public ModelAndView desbloquearMarSeleccionado(HttpSession session, @PathVariable ("nombreMar") String nombreMar){
        ModelMap mm = new ModelMap();

        UsuarioSesionDto usuarioSesion = (UsuarioSesionDto) session.getAttribute("usuarioLogueado");
        Jugador jugador = servicioJugador.buscarJugadorPorId(usuarioSesion.getId());
        Mar mar = servicioMapa.obtenerUnMarPorNombre(nombreMar);

        // aca tambien deberia ir la logica de si no existe jugador o si el mar no existe hacer algo?

            try{
               servicioMapa.desbloquearMarSegunElJugador(mar,jugador);
            }catch (NoSePuedodesbloquearElMarException e){
                mm.put("mar",mar);
                mm.put("jugadorMonedas",jugador.getMonedas());
                mm.put("errorAlDesbloquear",e.getMessage());
                return new ModelAndView("vistaMarBloqueado",mm);
            }

        return new ModelAndView("redirect:/mapa");
    }

    @RequestMapping("/validar")
    public ModelAndView validarSeleccionMar(HttpSession session){
        return null;
    }



}
