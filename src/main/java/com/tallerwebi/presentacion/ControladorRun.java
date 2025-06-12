package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    @RequestMapping("/Run")
    public ModelAndView iniciarPartida(ModelMap model) {
        return new ModelAndView("vistaRun", model);
    }

    // DESPUÉS DE UN TURNO: consulta si hay más cebos
    @RequestMapping("/Run/continuar")
    public ModelAndView continuarPartida(@ModelAttribute("cebos") Turno turno, ModelMap model) {
        if (servicioRun.hayCebo(turno)){
            String mensaje = servicioRun.jugarTurno(model);
            model.addAttribute("mensaje", mensaje);
            return new ModelAndView("redirect:/turno");
        }
            return new ModelAndView("redirect:/resumen-run");
    }

    //FINAL DEL TURNO
    @RequestMapping("/resumen-run")
    public ModelAndView mostrarResumenFinal(ModelMap model) {
        List<Pez> pecesPescados = servicioRun.obtenerPecesPescados(run); // Lista acumulada en la partida
        Double totalGanado = servicioRun.calcularGanancia(run); // Suma de precios
        Integer turnosJugados = servicioRun.getCantidadTurnosJugados(run);

        Resumen resumen = new Resumen(pecesPescados, totalGanado, turnosJugados);
        model.addAttribute("resumen", resumen);

        return new ModelAndView("vistaResumen", model); // Asegurate de tener vistaResumen.html
    }
}
