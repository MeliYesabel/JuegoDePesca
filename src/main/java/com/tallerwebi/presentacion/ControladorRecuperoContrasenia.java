package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.excepcion.UsuarioInexistenteLoginException;
import com.tallerwebi.dominio.servicio.ServicioUsuarioAuthI;
import com.tallerwebi.presentacion.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("recuperar-contrasenia")
    public ModelAndView recuperarContrasenia(@RequestParam(name = "email") String email) {
        ModelMap modelo = new ModelMap();
        if(email.isBlank()) {
            modelo.put("email_vacio", "El email es obligatorio");
        }else{
            try{//ARREGLAR CON REDIRECT
                servicioUsuario.buscarUsuarioPorMail(email);
                modelo.put("usuarioDto", new UsuarioDto());
                modelo.put("mensaje", "Se le envio un enlace de recuperacion a tu email.");
                return new ModelAndView("login-pescador", modelo);

            }catch (UsuarioInexistenteLoginException exception){
                modelo.put("no_existe", "El email ingresado no se encuentra registrado.");
                return new ModelAndView("recuperar-contrasenia", modelo);
            }
        }
        return new ModelAndView("recuperar-contrasenia", modelo);
    }
}
