package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.servicio.ServicioUsuarioI;
import com.tallerwebi.presentacion.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorLogin {

    private ServicioUsuarioI servicioUsuarioI;

    @Autowired
    public ControladorLogin(ServicioUsuarioI servicioUsuarioI) {
        this.servicioUsuarioI = servicioUsuarioI;
    }

    @GetMapping("login-pescador")
    public ModelAndView mostrarLogin() {
        ModelMap modeloLogin = new ModelMap();
        modeloLogin.put("usuarioDto", new UsuarioDto());
        return new ModelAndView("login-usuario", modeloLogin);
    }
}
