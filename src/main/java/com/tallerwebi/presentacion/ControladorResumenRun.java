package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ControladorResumenRun {

    @RequestMapping("/resumen")
    public ModelAndView mostrarResumen(HttpSession session) {
        ModelMap model = new ModelMap();

        return new ModelAndView("vistaResumen.html", model);
    }
}
