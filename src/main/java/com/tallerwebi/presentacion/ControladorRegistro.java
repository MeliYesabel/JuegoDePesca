package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.excepcion.ContraseniaInvalidaExcepcion;
import com.tallerwebi.dominio.excepcion.UsuarioExistenteExcepcion;
import com.tallerwebi.dominio.servicio.ServicioUsuarioAuthI;
import com.tallerwebi.presentacion.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ControladorRegistro {

    private ServicioUsuarioAuthI servicioUsuarioI;

    @Autowired
    public ControladorRegistro(ServicioUsuarioAuthI servicioUsuarioI) {
        this.servicioUsuarioI = servicioUsuarioI;
    }

    @RequestMapping(value = "/registro")
    public ModelAndView mostrarRegistro() {
        ModelMap modeloRegistro = new ModelMap();
        modeloRegistro.put("usuarioDto", new UsuarioDto());
        return new ModelAndView("registro", modeloRegistro);
    }

    @RequestMapping(value = "/registro", method = RequestMethod.POST)
    public ModelAndView registrarUsuario(@ModelAttribute("usuarioDto")UsuarioDto usuarioDto, RedirectAttributes redirectAttributes) {
        ModelMap datosModelados = new ModelMap();

        if(!estanTodosLosCamposCompletos(usuarioDto)){
            datosModelados.put("campos_vacios", "Todos los campos son obligatorios.Por favor complete los que faltan");
            return new ModelAndView("registro", datosModelados);
        }
        if(!usuarioDto.getContrasenia().equals(usuarioDto.getContraseniaRepetida())){
            datosModelados.put("error_repeticion", "Las contraseñas no coinciden.");
            return new ModelAndView("registro", datosModelados);
        }

        try {
            servicioUsuarioI.registrarUsuario(usuarioDto);
            redirectAttributes.addFlashAttribute("mensaje", "El usuario se registró exitosamente.");

        } catch (ContraseniaInvalidaExcepcion excepcion) {

            datosModelados.put("error_password_invalid", "Contraseña invalida: debe ser de longitud de 8 caracteres o mas y contener letras minusculas, mayusculas y algun numero");
            return new ModelAndView("registro", datosModelados);

        }catch (UsuarioExistenteExcepcion excepcion){
            datosModelados.put("error_usuario_unico", excepcion.getMessage());
            return new ModelAndView("registro", datosModelados);
        }

        return new ModelAndView("redirect:/login-pescador");

    }

    private boolean estanTodosLosCamposCompletos(UsuarioDto usuarioDto) {
        String emailIngresado = usuarioDto.getEmail();
        String contrasenia = usuarioDto.getContrasenia();
        String contraseniaRepetida = usuarioDto.getContraseniaRepetida();

        return !emailIngresado.isBlank() && !contrasenia.isBlank() && !contraseniaRepetida.isBlank();

    }
}
