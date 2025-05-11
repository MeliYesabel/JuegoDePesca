package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorLibroDeColeccion {

    @RequestMapping("/colecciones")
    public ModelAndView irALibroDeColecciones(){
        ModelMap modelLibroDeColecciones = new ModelMap();
        modelLibroDeColecciones.put("claveColeccion","Libro de Colecciones del Pescador");
        return new ModelAndView("vistaLibroDeColecciones.html", modelLibroDeColecciones);


    }

}
