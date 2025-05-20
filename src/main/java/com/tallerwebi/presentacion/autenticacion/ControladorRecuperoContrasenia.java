package com.tallerwebi.presentacion.autenticacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRecuperoContrasenia {

    @GetMapping("/recuperar-contrasenia")
    public ModelAndView mostrarFormRecuperoContrasenia() {
        ModelMap datosMap = new ModelMap();
        datosMap.put("usuarioDto", new UsuarioDto());
        return new ModelAndView("recuperar-contrasenia", datosMap);
    }

    //@PostMapping("/recu")
}
