package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorLogin {

    @RequestMapping("/loginPescador")
    public ModelAndView irALogin() {
        ModelMap modeloLogin = new ModelMap();
        modeloLogin.put("exito","estas en login");

        return new ModelAndView("loginPescador", modeloLogin);
    }

}
