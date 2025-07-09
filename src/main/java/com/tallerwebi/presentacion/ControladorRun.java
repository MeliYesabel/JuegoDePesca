package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Mar;
import com.tallerwebi.dominio.entidad.Run;
import com.tallerwebi.dominio.servicio.ServicioJugador;
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
    private ServicioJugador servicioJugador;
    private Run run = new Run();
    private ServicioMapa servicioMapa;

    @Autowired
    public ControladorRun(ServicioRun servicioRun,  ServicioMapa servicioMapa,ServicioJugador servicioJugador) {
        this.servicioJugador = servicioJugador;
        this.servicioRun = servicioRun;
        this.servicioMapa = servicioMapa;
    }

    @RequestMapping("/verificar-cebo")
    public ModelAndView verificarCeboJugador(HttpSession session) {
        Run run = (Run) session.getAttribute("run");
        Integer cebo = run.getCebo();
        run.setCebo(cebo -1);
        session.setAttribute("run", run);
        if (servicioRun.hayCeboJugador(run)) {
            return new ModelAndView("redirect:/turno");
        } else {
            return new ModelAndView("redirect:/resumen");
        }
    }
    @PostMapping("/run")
    public ModelAndView iniciarRun(@RequestParam("idMar") Long idMar, HttpSession session) {
        Long idUsuarioLogueado =(Long)  session.getAttribute("idUsuarioLogueado");
        Jugador jugador = servicioJugador.buscarJugadorPorId(idUsuarioLogueado);

        Integer cantidadCebo =  3; // hard codeo, aca van los cebo seteados previamente
        Mar marSeleccionado = servicioMapa.obtenerMarPorId(idMar);

        Run run = new Run();
        run.setCebo(cantidadCebo);
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