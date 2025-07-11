package com.tallerwebi.presentacion;

import com.tallerwebi.presentacion.dto.UsuarioSesionDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class ControladorAdmin {

    @GetMapping("/dashboard")
    public ModelAndView mostrarDashBoard(HttpServletRequest request){
        ModelMap datosModelado = new ModelMap();

        UsuarioSesionDto usuarioSesion = (UsuarioSesionDto) request.getSession().getAttribute("idUsuarioLogueado");
        String rol_admin = "ADMIN";

        if(usuarioSesion == null || !rol_admin.equalsIgnoreCase(usuarioSesion.getRol())){
            return new ModelAndView("redirect:/login-pescador");
        }

        datosModelado.put("nombreAdmin", usuarioSesion.getUsername());
        return new ModelAndView("dashboard", datosModelado);
    }
}
