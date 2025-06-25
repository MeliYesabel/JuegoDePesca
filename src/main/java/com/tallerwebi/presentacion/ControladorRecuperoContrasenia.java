package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.UsuarioAuth;
import com.tallerwebi.dominio.excepcion.UsuarioInexistenteException;
import com.tallerwebi.dominio.servicio.ServicioTokenRecupContrasenia;
import com.tallerwebi.dominio.servicio.ServicioUsuarioAuthI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorRecuperoContrasenia {

    private ServicioUsuarioAuthI servicioUsuario;
    private ServicioTokenRecupContrasenia serviceRecuparacion;
    @Autowired
    public ControladorRecuperoContrasenia(ServicioUsuarioAuthI servicioUsuarioI, ServicioTokenRecupContrasenia servicioRecuperacion) {
        this.servicioUsuario = servicioUsuarioI;
        this.serviceRecuparacion = servicioRecuperacion;
    }

    @GetMapping("recuperar-contrasenia")
    public ModelAndView mostrarFormRecuperoContrasenia() {
        return new ModelAndView("recuperar-contrasenia");
    }

    @PostMapping("recuperar-contrasenia")
    public ModelAndView recuperarContrasenia(HttpServletRequest request, @RequestParam(name = "email") String email) {
        ModelMap modelo = new ModelMap();
        if(email.isBlank()) {
            modelo.put("email_vacio", "El email es obligatorio");
        }else{
            try{
                UsuarioAuth usuario = servicioUsuario.buscarUsuarioPorMail(email);
                if (usuario == null) {
                    modelo.put("no_existe", "El email ingresado no se encuentra registrado.");
                } else {
                    System.out.println("DEBUG >> Email del usuario: " + usuario.getEmail());
                    if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
                        modelo.put("error", "El usuario no tiene un correo válido.");
                        return new ModelAndView("recuperar-contrasenia", modelo);
                    }

                    serviceRecuparacion.enviarTokenRecuperacion(usuario, request);
                    modelo.put("mensaje", "Se le envió un enlace de recuperación a tu email.");
                }

            }catch (UsuarioInexistenteException exception){
                modelo.put("no_existe", "El email ingresado no se encuentra registrado.");
            }
        }
        return new ModelAndView("recuperar-contrasenia", modelo);
    }
}
