package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.ObjetoInexistenteException;
import com.tallerwebi.dominio.excepcion.ParametroInvalidoException;
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
    private RepositorioJugador repositorioJugador;
    private Jugador jugador;

    @Autowired
    public ControladorPersonaje(ServicioJugador servicioJugador,RepositorioJugador repositorioJugador) {
        this.servicioJugador = servicioJugador;
        this.repositorioJugador = repositorioJugador;
        jugador =  servicioJugador.inicializarJugador();

    }

    @RequestMapping(value = "/personaje",method = RequestMethod.GET)
    public ModelAndView irAPersonaje(HttpSession session){
        ModelMap model = new ModelMap();


        //Jugador jugador = new Jugador();
       // repositorioJugador.guardarJugador(jugador);

       session.getAttribute("jugador");
        model.put("clavePersonaje","Esta es la pantalla del personaje");
        model.put("jugador", jugador);
        model.put("objetosDelJugador",jugador.getObjetosComprados()); //si borro esto corre
        return new ModelAndView("vistaPersonaje.html",model);
    }

    @RequestMapping(value = "/vistaCaniasDelPersonaje")
    public ModelAndView verCaniasDisponiblesDelPersonaje(HttpSession session){
        ModelMap model = new ModelMap();

        jugador.agregarObjeto(new Objeto(100.0,"caña")); //lo agregue ahora pero esta mal
        session.getAttribute("jugador");
        model.put("jugador", jugador);
        model.put("objetosDelJugador",jugador.getObjetosComprados());
        return new ModelAndView("vistaEquipamiento.html",model);

    }

    @RequestMapping(value = "/equipamiento", method = RequestMethod.POST)
    public ModelAndView equiparCania(HttpSession session, @RequestParam Integer idObjeto) {
        ModelMap model = new ModelMap();

        Jugador jugador = (Jugador)session.getAttribute("jugador");

       if(jugador == null){
           model.put("error", "No hay sesiones activas para este jugador");
           return new ModelAndView("vistaPersonaje.html",model);
       }

       try{
           servicioJugador.equipaCaniaAPersonaje(jugador,idObjeto);
           model.put("mensaje", "Caña equipa correctamente");
           model.put("jugador",jugador);
           return new ModelAndView("vistaEquipamiento.html",model); //la vista equipamiento tiene la lista de cañas para equipar

       }catch (ParametroInvalidoException | ObjetoInexistenteException e){
           model.put("error", e.getMessage());
           model.put("jugador",jugador);
           return new ModelAndView("vistaPersonaje.html",model); // la vista personaje nos permite ir a la vista equipamiento o a las demas
       }

    }
}
