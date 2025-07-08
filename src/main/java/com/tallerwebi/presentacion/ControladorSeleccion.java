package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Mar;
import com.tallerwebi.dominio.entidad.Run;
import com.tallerwebi.dominio.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        /*En teoria aca deberia estar la logica de sumar cebos al run ademas cada run
        * deberia estar en una session y guardar en la base de datos
        * esto ademas de sumar descantaria a la cantidad de cebos del jugador pero si se arrepiente esos cebos deberian volver al jugador */
        Long idUsuarioLogueado =(Long)  session.getAttribute("idUsuarioLogueado");
        Jugador jugador = servicioJugador.buscarJugadorPorId(idUsuarioLogueado);
        Run run =(Run) session.getAttribute("run");

        if (run == null) {
            mm.put("error", "Error al iniciar la pesca");
            return new ModelAndView("redirect:/mapa",mm);
        }

       // Integer cantCebo = servicioJugador.obtenerCantDeCebos(jugador);
        Integer cantCarnada = jugador.getCant_carnada();
        if (cantCarnada == null || cantCarnada <= 0){
            mm.put("mensajeError", "El jugador no tiene cebos. Compre cebos en tienda");
            mm.put("runCebo", run.getCebo());
            mm.put("mar", run.getMar());
            return new ModelAndView("vistaSeleccion", mm);
        }

        //agrego cebo
        servicioRun.agregarCebo(run,1);
        session.setAttribute("run", run);

        // resto cebo
        jugador.setCant_carnada(jugador.getCant_carnada()-1);
        servicioJugador.guardar(jugador);

        // si toca el boton me tendria que descontar la cantida de cebos del jugador
        // si toco el boton mapa no los descuenta

        mm.put("runCebo", run.getCebo());
        mm.put("mar",run.getMar());


        return new ModelAndView("vistaSeleccion",mm);
   }


   public ModelAndView validacionesDelJuegoAntesDeCambiarDeVistaARun(HttpSession session,@PathVariable ("nombreMar") String nombreMar){
       ModelMap modelMap = new ModelMap();
       Long idUsuarioLogueado =(Long)  session.getAttribute("idUsuarioLogueado");
       Jugador jugador = servicioJugador.buscarJugadorPorId(idUsuarioLogueado);

       Mar mar = servicioMapa.obtenerUnMarPorNombre(nombreMar);



      /* j.setCant_carnada(0);
       if (j.getCant_carnada() ==0){
           modelMap.put("errorCantCarnada","El jugador no tiene carnadas");
           return new ModelAndView("vistaSeleccion", modelMap);
       }

       j.setCant_carnada(6);
       if (!servicioSeleccion.laCantidadDeCarnadaEsMenorQueCindo(j.getCant_carnada())){
           modelMap.put("errorCantCarnada","El jugador no puede tener mas de cinco carnada");
           return new ModelAndView("vistaSeleccion", modelMap);
       }*/
       modelMap.put("mar",mar);


       return new ModelAndView("vistaRun",modelMap);
   }
}
