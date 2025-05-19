package com.tallerwebi.presentacion.autenticacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {

    @RequestMapping(value = "/registro", method = RequestMethod.GET)
    public ModelAndView mostrarRegistro() {
        ModelMap modeloRegistro = new ModelMap();
        modeloRegistro.put("usuarioDto", new UsuarioDto());
        return new ModelAndView("registro", modeloRegistro);
    }
}
