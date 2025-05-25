package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRun {

    private ServicioRun servicioRun;

    @Autowired
    public ControladorRun(ServicioRun servicioRun) {
        this.servicioRun = servicioRun;
    }

    // PRIMER INGRESO a /Run: se muestra pantalla de inicio
    @RequestMapping("/Run")
    public ModelAndView mostrarInicio(ModelMap model) {
        return new ModelAndView("vistaRun", model); //
    }

    // DESPUÉS DE UN TURNO: consulta si hay más cebos
    @RequestMapping("/Run/continuar")
    public ModelAndView continuarPartida(@ModelAttribute("cebos") Integer cebos, ModelMap model) {
        if (servicioRun.hayCebo(cebos)) {
            String mensaje = servicioRun.jugarTurno(model);
            model.addAttribute("mensaje", mensaje);
            return new ModelAndView("redirect:/turno");
        } else {
            return new ModelAndView("redirect:/resumen-run");
        }
    }
}
