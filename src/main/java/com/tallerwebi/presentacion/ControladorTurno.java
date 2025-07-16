package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Pez;
import com.tallerwebi.dominio.entidad.Run;
import com.tallerwebi.dominio.entidad.Turno;
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
        Run run = (Run) session.getAttribute("run");

        Integer cantidadCebo = run.getCebo();

        Turno turno = (Turno) session.getAttribute("turnoActual");

        if (turno == null) {
            turno = servicioTurno.crearUnTurno();
            List<Pez> peces = servicioTurno.obtenerTresPecesAleatorios();
            turno.setPecesGenerados(peces);
            session.setAttribute("turnoActual", turno);
        }

        model.put("cantidadCarnada", cantidadCebo);

        model.addAttribute("peces", turno.getPecesGenerados());
        return new ModelAndView("vistaTurno.html", model);
    }


    @RequestMapping("/turno/resultado")
    public ModelAndView seleccionarPez(@RequestParam("id") Long idPez, ModelMap model, HttpSession session) {
        Run run = (Run) session.getAttribute("run");
        Pez pezSeleccionado = servicioTurno.pescarPezPorId(idPez);
        Boolean pesco = servicioTurno.pesca(pezSeleccionado);

        model.addAttribute("pesco", pesco);
        model.addAttribute("pez", pezSeleccionado);

        if (pesco) {
            run.agregarPecesPescados(pezSeleccionado);
            session.setAttribute("run", run);
        }

        session.removeAttribute("turnoActual");
        return new ModelAndView("pezPescadoVista", model);
    }
}
