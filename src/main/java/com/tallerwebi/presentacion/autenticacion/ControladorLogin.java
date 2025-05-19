package com.tallerwebi.presentacion.autenticacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorLogin {

    @GetMapping("/login-pescador")
    public ModelAndView mostrarLogIn() {
        ModelMap modeloLogin = new ModelMap();
        modeloLogin.put("usuarioDto", new UsuarioDto());
        return new ModelAndView("login-pescador", modeloLogin);
    }
}
