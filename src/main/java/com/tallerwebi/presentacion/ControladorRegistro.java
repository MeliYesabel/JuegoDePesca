package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.servicio.ServicioUsuarioI;
import com.tallerwebi.presentacion.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {

    private ServicioUsuarioI servicioUsuario;

    @Autowired
    public ControladorRegistro(ServicioUsuarioI servicioUsuarioI) {
        this.servicioUsuario = servicioUsuarioI;
    }

    @RequestMapping(value = "/registro")
    public ModelAndView mostrarRegistro() {
        ModelMap modeloRegistro = new ModelMap();
        modeloRegistro.put("usuarioDto", new UsuarioDto());
        return new ModelAndView("registro", modeloRegistro);
    }
}
