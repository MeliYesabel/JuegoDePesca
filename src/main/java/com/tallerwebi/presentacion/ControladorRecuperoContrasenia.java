package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.servicio.ServicioUsuarioAuthI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRecuperoContrasenia {

    private ServicioUsuarioAuthI servicioUsuario;

    @Autowired
    public ControladorRecuperoContrasenia(ServicioUsuarioAuthI servicioUsuarioI) {
        this.servicioUsuario = servicioUsuarioI;
    }

    @GetMapping("recuperar-contrasenia")
    public ModelAndView mostrarFormRecuperoContrasenia() {
        return new ModelAndView("recuperar-contrasenia");
    }
}
