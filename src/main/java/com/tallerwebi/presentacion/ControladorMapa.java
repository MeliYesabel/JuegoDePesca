package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorMapa {


    @RequestMapping("/vistaMapa")
    public ModelAndView irVistaMapa(){
        return new ModelAndView("vistaMapa");
    }

    @RequestMapping("/vistaLogros")
    public ModelAndView irVistaLogros(){
        return new ModelAndView("vistaLogros");
    }

    @RequestMapping("/vistaTienda")
    public ModelAndView irVistaTienda(){
        return new ModelAndView("vistaTienda");
    }




}
