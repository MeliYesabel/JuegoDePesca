package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Mar;
import com.tallerwebi.dominio.servicio.ServicioMapa;
import com.tallerwebi.dominio.servicio.ServicioSeleccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ControladorSeleccion {

    /*interfaz de servicio*/
    private ServicioSeleccion servicioSeleccion;
    private ServicioMapa servicioMapa;

    /*creo el contructor para usar la interfaz de servicio*/
    @Autowired
    public ControladorSeleccion(ServicioSeleccion servicioSeleccion,ServicioMapa servicioMapa) {
        this.servicioSeleccion = servicioSeleccion;
        this.servicioMapa = servicioMapa;
    }

   public ModelAndView validacionesDelJuegoAntesDeCambiarDeVistaARun(HttpSession session,@PathVariable ("nombreMar") String nombreMar){
       ModelMap modelMap = new ModelMap();
       Jugador j = (Jugador) session.getAttribute("jugador");
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
