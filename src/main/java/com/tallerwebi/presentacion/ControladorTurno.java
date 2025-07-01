package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Pez;
import com.tallerwebi.dominio.servicio.ServicioRun;
import com.tallerwebi.dominio.servicio.ServicioTurno;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ControladorTurno {

    private ServicioRun servicioRun;
    private ServicioTurno servicioTurno;

    public ControladorTurno(ServicioRun servicioRun, ServicioTurno servicioTurno) {
        this.servicioRun = servicioRun;
        this.servicioTurno = servicioTurno;
    }


    @RequestMapping("/turno")
    public ModelAndView iniciarTurno(HttpSession session) {
        ModelMap model = new ModelMap();
        Long idMar = 0L;
        List<Pez> peces = servicioTurno.obtenerTresPecesAleatorios();
        model.addAttribute("peces", peces);
        return new ModelAndView("vistaTurno.html", model);
    }


    @RequestMapping("/tunro/resultado")
    public ModelAndView seleccionarPez(@RequestParam("id") Long idPez, ModelMap model) {
        Pez pezPescado = servicioTurno.pescarPezPorId(idPez);
        model.addAttribute("pezPescado", pezPescado);
        return new ModelAndView("pezPescadoVista.html", model);
    }
}
