package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorLogros {

    @RequestMapping("/logros")
    public ModelAndView irLogros(){
        ModelMap model = new ModelMap();
        model.put("claveLogros","Este es el libro de logos");
        return new ModelAndView("vistaLogros.html", model);
    }
}
