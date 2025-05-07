package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorTienda {

    @RequestMapping("/tienda")
    public ModelAndView irTienda(){
        ModelMap model = new ModelMap();
        model.put("claveTienda","Esta es la tienda");
        return new ModelAndView("vistaTienda.html", model);
    }
}
