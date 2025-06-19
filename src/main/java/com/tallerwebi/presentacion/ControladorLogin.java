package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.servicio.ServicioUsuarioAuthI;
import com.tallerwebi.presentacion.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorLogin {

    private ServicioUsuarioAuthI servicioUsuarioI;

    @Autowired
    public ControladorLogin(ServicioUsuarioAuthI servicioUsuarioI) {
        this.servicioUsuarioI = servicioUsuarioI;
    }

    @GetMapping("login-pescador")
    public ModelAndView mostrarLogin() {
        ModelMap modeloLogin = new ModelMap();
        modeloLogin.put("usuarioDto", new UsuarioDto());
        return new ModelAndView("login-usuario", modeloLogin);
    }
}
