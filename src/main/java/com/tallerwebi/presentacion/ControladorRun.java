package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ControladorRun {
    /*RUN tienen la logica que se fija que si la cantidad de carnadas es myor a cero
    * si la pasiva es distinta de null
    * si da verdadero te redirige a vista->  "Run"*/

    private ServicioRun servicioRun;
    private Run run = new Run();

    @Autowired
    public ControladorRun(ServicioRun servicioRun) {
        this.servicioRun = servicioRun;
    }

    // PRIMER INGRESO a /Run: se muestra pantalla de inicio
    @RequestMapping("/run")
    public ModelAndView iniciarPartida(HttpSession session) {
        ModelMap model = new ModelMap();

        //Run run = (Run) session.getAttribute("run");
        //model.put("run", run);
        //Jugador jugador = (Jugador) session.getAttribute("jugador");
        //model.put("jugador", jugador);
        //model.put("cantidadCebo", jugador.getCantidadCeboSeleccionado());
        //model.put("cantidadCebo", run.getCebo());
        return new ModelAndView("vistaRun.html", model);
    }

    @RequestMapping("/Run/verificar-cebo")
    public ModelAndView verificarCeboJugador(HttpSession session) {
        Run run = (Run) session.getAttribute("run");

        if (run == null || run.getJugador() == null) {
            return new ModelAndView("redirect:/run"); // vuelve al inicio si falta algo
        }

        if (servicioRun.hayCeboJugador(run)) {
            return new ModelAndView("redirect:/turno");
        } else {
            return new ModelAndView("redirect:/resumen");
        }
    }
}
