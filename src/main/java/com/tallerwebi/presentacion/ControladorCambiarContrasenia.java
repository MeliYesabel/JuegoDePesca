package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.TokenRecuperacionContrasenia;
import com.tallerwebi.dominio.excepcion.ContraseniaInvalidaExcepcion;
import com.tallerwebi.dominio.servicio.ServicioTokenRecupContrasenia;
import com.tallerwebi.dominio.servicio.ServicioUsuarioAuthI;
import com.tallerwebi.presentacion.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
public class ControladorCambiarContrasenia {
    private ServicioTokenRecupContrasenia serviceRecuparacion;
    private ServicioUsuarioAuthI servicioUsuarioAuth;

    @Autowired
    public ControladorCambiarContrasenia(ServicioTokenRecupContrasenia serviceRecuparacion, ServicioUsuarioAuthI servicioUsuario) {
        this.serviceRecuparacion = serviceRecuparacion;
        this.servicioUsuarioAuth = servicioUsuario;

    }

    @GetMapping("/cambiar-contrasenia")
    public ModelAndView mostrarFormularioCambio(@RequestParam("token") String token){
        ModelMap modelo = new ModelMap();
        modelo.put("usuarioDto", new UsuarioDto());
        TokenRecuperacionContrasenia passwordResetToken = serviceRecuparacion.buscarPorToken(token);

        if(passwordResetToken==null || passwordResetToken.getExpiracionToken().isBefore(LocalDateTime.now())){
            modelo.put("error", "Token invalido o expirado");
            return new ModelAndView("cambiar-contrasenia", modelo);
        }
        modelo.put("token",token);
        return new ModelAndView("cambiar-contrasenia",modelo);
    }

    @PostMapping("/cambiar-contrasenia")
    public ModelAndView procesarCambio(@ModelAttribute("usuarioDto")UsuarioDto usuarioDto, @RequestParam("token") String token){
        ModelMap modelo = new ModelMap();
        modelo.put("usuarioDto", usuarioDto); // <-- ¡IMPORTANTE!

        if(!estanTodosLosCamposCompletos(usuarioDto)){
            modelo.put("campos_vacios", "Todos los campos son obligatorios.Por favor complete los que faltan");
            return new ModelAndView("cambiar-contrasenia", modelo);
        }
        if(!usuarioDto.getContrasenia().equals(usuarioDto.getContraseniaRepetida())){
            modelo.put("error_repeticion", "Las contraseñas no coinciden.");
            return new ModelAndView("cambiar-contrasenia", modelo);
        }

        try {
            TokenRecuperacionContrasenia tokenRegistrado = serviceRecuparacion.buscarPorToken(token);
            if(tokenRegistrado==null || tokenRegistrado.getExpiracionToken().isBefore(LocalDateTime.now())){
                modelo.put("error", "Token invalido o expirado");
                return new ModelAndView("cambiar-contrasenia",modelo);
            }
            servicioUsuarioAuth.actualizarContrasenia(usuarioDto, tokenRegistrado);
            modelo.put("mensaje", "El cambio de contraseña fue exitoso.");

        } catch (ContraseniaInvalidaExcepcion excepcion) {
            modelo.put("error_password_invalid", "Contraseña invalida: debe ser de longitud de 8 caracteres o mas y contener letras minusculas, mayusculas y algun numero");

        }

        return new ModelAndView("cambiar-contrasenia", modelo);

    }

    private boolean estanTodosLosCamposCompletos(UsuarioDto usuarioDto) {
        String contrasenia = usuarioDto.getContrasenia();
        String contraseniaRepetida = usuarioDto.getContraseniaRepetida();

        return !contrasenia.isBlank() && !contraseniaRepetida.isBlank();

    }
}
