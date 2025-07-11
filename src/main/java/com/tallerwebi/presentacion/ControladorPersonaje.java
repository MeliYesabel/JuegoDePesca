package com.tallerwebi.presentacion;


import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.excepcion.ObjetoInexistenteException;
import com.tallerwebi.dominio.excepcion.ParametroInvalidoException;
import com.tallerwebi.dominio.servicio.ServicioJugador;
import com.tallerwebi.presentacion.dto.UsuarioSesionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ControladorPersonaje {
    private ServicioJugador servicioJugador;

   // private Jugador jugador;

    @Autowired
    public ControladorPersonaje(ServicioJugador servicioJugador) {
        this.servicioJugador = servicioJugador;

       // jugador =  servicioJugador.inicializarJugador();

    }



   @RequestMapping(value="/personaje", method = RequestMethod.GET)
    public ModelAndView irAPersonaje(HttpSession session){
        ModelMap model = new ModelMap();

       // Jugador jugador = (Jugador) session.getAttribute("jugador");
       Long idUsuarioLogueado =(Long)  session.getAttribute("idUsuarioLogueado");
       Jugador jugador = servicioJugador.buscarJugadorPorId(idUsuarioLogueado);

      /*  if (jugador == null) {
            jugador = servicioJugador.inicializarJugador(); // solo entra acá la primera vez
            session.setAttribute("jugador", jugador);
        } else{
            jugador = repositorioJugador.buscarJugador(jugador.getId());
            session.setAttribute("jugador", jugador);
        }*/

        model.put("jugador", jugador);
       // model.put("clavePersonaje", "Esta es la pantalla personaje");
        return new ModelAndView("vistaPersonaje.html", model);
    }



    @RequestMapping(value="/objeto", method = RequestMethod.GET)
    public ModelAndView irAObjeto(HttpSession session){
        ModelMap model = new ModelMap();

        //Jugador jugador = (Jugador) session.getAttribute("jugador");
        Long idUsuarioLogueado =(Long)  session.getAttribute("idUsuarioLogueado");
        Jugador jugador = servicioJugador.buscarJugadorPorId(idUsuarioLogueado);

        if (jugador == null) {
            model.put("error", "No hay sesión activa");
            return new ModelAndView("vistaPersonaje.html", model);
        }

        // Recargo jugador con objetos actualizados
       // jugador = repositorioJugador.buscarJugador(jugador.getId());
        servicioJugador.getRepositorioJugador().buscarJugador(jugador.getId());
       // session.setAttribute("jugador", jugador);

        model.put("jugador", jugador);
        model.put("objetosDelJugador", jugador.getObjetosComprados());
        model.put("caniaActiva", jugador.getCaniaActiva());

        return new ModelAndView("objetoDelJugador.html", model);
    }


    @RequestMapping(value = "/equipar", method = RequestMethod.POST)
    public ModelAndView equiparCania(HttpSession session, @RequestParam Long idObjeto) {
        ModelMap model = new ModelMap();

       // Jugador jugador = (Jugador) session.getAttribute("jugador");
        Long idUsuarioLogueado =(Long)  session.getAttribute("idUsuarioLogueado");
        Jugador jugador = servicioJugador.buscarJugadorPorId(idUsuarioLogueado);

        if (jugador == null) {
            model.put("error", "No hay sesión activa para este jugador");
            return new ModelAndView("vistaPersonaje.html", model);
        }

        try {
          Boolean equipada =  servicioJugador.equipaCaniaAPersonaje(jugador, idObjeto);

          if(equipada){
              jugador = servicioJugador.buscarJugadorPorId(jugador.getId());
              model.put("mensaje", "Caña equipada correctamente");
          }

            // Recargo jugador luego de equipar para reflejar cambios
          //  jugador = repositorioJugador.buscarJugador(jugador.getId());
           // servicioJugador.getRepositorioJugador().buscarJugador(jugador.getId());
            //session.setAttribute("jugador", jugador);


        } catch (ParametroInvalidoException | ObjetoInexistenteException e) {
            model.put("error", e.getMessage());
        }

        model.put("jugador", jugador);
        model.put("objetosDelJugador", jugador.getObjetosComprados());
        model.put("caniaActiva", jugador.getCaniaActiva());

        return new ModelAndView("objetoDelJugador.html", model);
    }


}
