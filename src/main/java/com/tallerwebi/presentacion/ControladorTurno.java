package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/turno")
public class ControladorTurno {

    private final ServicioTurnoImpl servicioTurno;
    private final ServicioRunImpl servicioRun;

    public ControladorTurno(ServicioTurnoImpl servicioTurno, ServicioRunImpl servicioRun) {
        this.servicioTurno = servicioTurno;
        this.servicioRun = servicioRun;
    }

    @GetMapping("/{numero}")
    public ModelAndView jugarTurno(@PathVariable Integer numero, Model model) {
        Turno turno = new Turno();
        List<Pez> pecesGenerados = servicioTurno.generarPeces(servicioRun.getMar());
        turno.setPecesGenerados(pecesGenerados);
        model.addAttribute("turno", turno);
        model.addAttribute("numero", numero);
        return new ModelAndView("vista-turno", (Map<String, ?>) model);
    }
}

