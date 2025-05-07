package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {

    @RequestMapping("/registro")
    public ModelAndView irARegistro() {
        ModelMap modeloRegistro = new ModelMap();
        modeloRegistro.put("exito","estas en registro");

        return new ModelAndView("loginPescador", modeloRegistro);
    }
}
