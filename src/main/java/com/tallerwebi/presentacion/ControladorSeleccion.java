package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.ServicioMapa;
import com.tallerwebi.dominio.ServicioSeleccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorSeleccion {

    /*interfaz de servicio*/
    private ServicioSeleccion servicioSeleccion;

    /*creo el contructor para usar la interfaz de servicio*/
    @Autowired
    public ControladorSeleccion(ServicioSeleccion servicioSeleccion) {
        this.servicioSeleccion = servicioSeleccion;
    }

   public ModelAndView validacionesDelJuegoAntesDeCambiarDeVistaARun(Jugador jugador){
       ModelMap modelMap = new ModelMap();
       Jugador j = new Jugador();

       j.setCant_carnada(0);
       if (j.getCant_carnada() ==0){
           modelMap.put("errorCantCarnada","El jugador no tiene carnadas");
           return new ModelAndView("vistaSeleccion", modelMap);
       }

       j.setCant_carnada(6);
       if (!servicioSeleccion.laCantidadDeCarnadaEsMenorQueCindo(j.getCant_carnada())){
           modelMap.put("errorCantCarnada","El jugador no puede tener mas de cinco carnada");
           return new ModelAndView("vistaSeleccion", modelMap);
       }

       return new ModelAndView("vistaRun");
   }
}
