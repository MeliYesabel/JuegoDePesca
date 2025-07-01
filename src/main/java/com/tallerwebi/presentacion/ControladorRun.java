package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Mar;
import com.tallerwebi.dominio.entidad.Run;
import com.tallerwebi.dominio.servicio.ServicioMapa;
import com.tallerwebi.dominio.servicio.ServicioRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ControladorRun {
    /*RUN tienen la logica que se fija que si la cantidad de carnadas es myor a cero
    * si la pasiva es distinta de null
    * si da verdadero te redirige a vista->  "Run"*/

    private ServicioRun servicioRun;
    private Run run = new Run();
    private ServicioMapa servicioMapa;

    @Autowired
    public ControladorRun(ServicioRun servicioRun,  ServicioMapa servicioMapa) {
        this.servicioRun = servicioRun;
        this.servicioMapa = servicioMapa;
    }

    // PRIMER INGRESO a /Run: se muestra pantalla de inicio
   /* @RequestMapping("/run")
    public ModelAndView iniciarPartida(HttpSession session) {
        ModelMap model = new ModelMap();

        //Run run = (Run) session.getAttribute("run");
        //model.put("run", run);
        //Jugador jugador = (Jugador) session.getAttribute("jugador");
        //model.put("jugador", jugador);
        //model.put("cantidadCebo", jugador.getCantidadCeboSeleccionado());
        //model.put("cantidadCebo", run.getCebo());
        return new ModelAndView("vistaRun.html", model);
    }*/   // comento para ir a run desde el boton empezar

    @RequestMapping("/verificar-cebo")
    public ModelAndView verificarCeboJugador(HttpSession session) {
        Run run = (Run) session.getAttribute("run");
        if (servicioRun.hayCeboJugador(run)) {
            return new ModelAndView("redirect:/turno");
        } else {
            return new ModelAndView("redirect:/resumen");
        }
    }
    @PostMapping("/run")
    public ModelAndView iniciarRun(@RequestParam("idMar") Long idMar, HttpSession session) {
        Jugador jugador = (Jugador) session.getAttribute("jugador");

        Mar marSeleccionado = servicioMapa.obtenerMarPorId(idMar);

        Run run = new Run();
        run.setJugador(jugador);
        run.setMar(marSeleccionado);
        run.setCebo(run.getCebo());
        servicioRun.guardarRun(run);

        session.setAttribute("run", run);

        ModelAndView mav = new ModelAndView("vistaRun");
        mav.addObject("run", run);
        return mav;
    }
}