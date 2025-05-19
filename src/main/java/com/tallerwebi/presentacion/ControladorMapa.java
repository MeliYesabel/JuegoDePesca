package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorMapa {

    @RequestMapping("/vistaLogros")
    public ModelAndView irAVistaLogros(){
        return new ModelAndView("vistaLogros");
    }

    @RequestMapping("/vistaTienda")
    public ModelAndView irAVistaTienda(){
        return new ModelAndView("vistaTienda");
    }

    @RequestMapping("/vistaSeleccion")
    public ModelAndView irAVistaSeleccion() {
        return new ModelAndView("vistaSeleccion");
    }


}
