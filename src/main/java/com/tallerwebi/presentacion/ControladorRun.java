package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.*;
import com.tallerwebi.dominio.servicio.ServicioJugador;
import com.tallerwebi.dominio.servicio.ServicioMapa;
import com.tallerwebi.dominio.servicio.ServicioRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

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
        ModelMap mm = new ModelMap();
        Long idUsuarioLogueado =(Long)  session.getAttribute("idUsuarioLogueado");
        Jugador jugador = servicioJugador.buscarJugadorPorId(idUsuarioLogueado);
        Run run =(Run) session.getAttribute("run");

        Integer cantidadCebo = run.getCebo(); // hard codeo, aca van los cebo seteados previamente

        Mar marSeleccionado = servicioMapa.obtenerMarPorId(idMar);

       // Run run = new Run();
       // run.setCebo(cantidadCebo);
        run.setJugador(jugador);
        run.setMar(marSeleccionado);
        servicioRun.guardarRun(run);

        //session.setAttribute("run", run);
        mm.put("cebos", run.getCebo());
        mm.put("activa",jugador.getCaniaActiva().getNombreImagen());
        ModelAndView mav = new ModelAndView("vistaRun",mm);
        mav.addObject("run", run);
        return mav;
    }

    @RequestMapping("/resumen")
    public ModelAndView mostrarResumen(HttpSession session) {
        ModelMap model = new ModelMap();
        Run run = (Run) session.getAttribute("run");

        Long idUsuarioLogueado =(Long)  session.getAttribute("idUsuarioLogueado");
        Jugador jugador = servicioJugador.buscarJugadorPorId(idUsuarioLogueado);

        if (run == null) {
            model.put("error", "No hay una partida activa. Volvé al inicio.");
            return new ModelAndView("vistaSeleccion.html", model);
        }

        Integer ganancia = run.obtenerganacia();
        model.put("ganancia", ganancia != null ? ganancia : 0);

        List<Pez> pecesPescados = run.getPecesPescados();
        Integer cantidadPecesPescados = (pecesPescados != null) ? pecesPescados.size() : 0;
        model.put("cantidadPeces", cantidadPecesPescados);

        jugador.sumarGanancia(ganancia);
        servicioJugador.getRepositorioJugador().actualizarDatosDeJugadorYaExistente(jugador);

        return new ModelAndView("vistaResumen", model);
    }
}