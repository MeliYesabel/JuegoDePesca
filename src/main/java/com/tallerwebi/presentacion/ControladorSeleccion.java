package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Mar;
import com.tallerwebi.dominio.entidad.Run;
import com.tallerwebi.dominio.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ControladorSeleccion {

    /*interfaz de servicio*/
    private ServicioSeleccion servicioSeleccion;
    private ServicioMapa servicioMapa;
    private ServicioJugador servicioJugador;
    private ServicioRun servicioRun;

    /*creo el contructor para usar la interfaz de servicio*/
    @Autowired
    public ControladorSeleccion(ServicioSeleccion servicioSeleccion,ServicioMapa servicioMapa, ServicioJugador servicioJugador,ServicioRun servicioRun) {
        this.servicioSeleccion = servicioSeleccion;
        this.servicioMapa = servicioMapa;
        this.servicioJugador = servicioJugador;
        this.servicioRun = servicioRun;
    }

    @PostMapping("/sumar-cebos")
   public ModelAndView sumar(HttpSession session) {
        ModelMap mm= new ModelMap();

        Long idUsuarioLogueado =(Long)  session.getAttribute("idUsuarioLogueado");
        Jugador jugador = servicioJugador.buscarJugadorPorId(idUsuarioLogueado);
        Run run =(Run) session.getAttribute("run");

        if (run == null) {
            mm.put("error", "Error al iniciar la pesca");
            return new ModelAndView("redirect:/mapa",mm);
        }

        Integer cantCarnada = jugador.getCant_carnada();
        if (cantCarnada == null || cantCarnada <= 0){
            mm.put("mensajeError", "El jugador no tiene cebos. Compre cebos en tienda");
            mm.put("runCebo", run.getCebo());
            mm.put("mar", run.getMar());
            mm.put("jugador", jugador);
            mm.put("monedas", jugador.getMonedas());
            return new ModelAndView("vistaSeleccion", mm);
        }

        //agrego cebo
        servicioRun.agregarCebo(run,1);

        // actualizo en la session
        session.setAttribute("run", run);


        // resto cebo
        jugador.setCant_carnada(jugador.getCant_carnada()-1);
        servicioJugador.guardar(jugador);

        //
        Integer cebosAgregados = (Integer) session.getAttribute("cebosAgregados");

        if (cebosAgregados == null) {
            cebosAgregados = 0;
        }

        cebosAgregados++;

        session.setAttribute("cebosAgregados", cebosAgregados);


        mm.put("runCebo", run.getCebo());
        mm.put("mar",run.getMar());
        mm.put("monedas", jugador.getMonedas());
        return new ModelAndView("vistaSeleccion",mm);
   }

   // como hacer para que nose pierdan las carnadas
    @RequestMapping("/volver")
    public ModelAndView volver(HttpSession session){
        Long idUsuarioLogueado =(Long)  session.getAttribute("idUsuarioLogueado");
        Jugador jugador = servicioJugador.buscarJugadorPorId(idUsuarioLogueado);
        Integer cebosTiene = jugador.getCant_carnada();

        Integer cebosUsados = (Integer) session.getAttribute("cebosAgregados");
        if (cebosUsados != null) {
            jugador.setCant_carnada(cebosTiene + cebosUsados);
            servicioJugador.guardar(jugador);
            session.removeAttribute("cebosAgregados");
            session.removeAttribute("run");

        }
        return new ModelAndView("redirect:/mapa");
   }


}
