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
        if (listaMar.isEmpty()) {
            modelMap.put("mensaje", "No se puede obtener la lista de mars");
            return new ModelAndView("login-usuario",modelMap);
        }
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

        /*utilizo una session*/
        UsuarioSesionDto usuarioSesion = (UsuarioSesionDto) session.getAttribute("usuarioLogueado");
        Jugador jugador = servicioJugador.buscarJugadorPorId(usuarioSesion.getId());

        Mar mar = servicioMapa.obtenerUnMarPorNombre(nombreMar);
        boolean estado = servicioMapa.obtenerElEstadoDelMarSegunELJugador(mar,jugador);// -> base datos join usuario es
         if (estado){
             mm.put("marError", "El mar seleccionado esta bloqueado");
             return new ModelAndView("vistaMarBloqueado",mm);
         }

        mm.put("mar", mar);

        return new ModelAndView("vistaSeleccion",mm);
    }


}
