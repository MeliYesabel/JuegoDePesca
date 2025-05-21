package com.tallerwebi.presentacion.autenticacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {

    @RequestMapping(value = "/registro", method = RequestMethod.GET)
    public ModelAndView mostrarRegistro() {
        ModelMap modeloRegistro = new ModelMap();
        modeloRegistro.put("usuarioDto", new UsuarioDto());
        return new ModelAndView("registro", modeloRegistro);
    }

    @RequestMapping(value = "/registro", method = RequestMethod.POST)
    public ModelAndView registrarUsuario(@ModelAttribute("usuarioDto")UsuarioDto usuarioDto){
        ModelMap datosModelados = new ModelMap();

        if(!estanTodosLosCamposCompletos(usuarioDto)){
            
        }
        return null;
        //validar que todos los campos no esten vacios: "Todos los campos son obligatorios. 
        //Por favor complete los que faltan -> Controlador

        //validar password (longitud de 8 caracteres, tiene numero, caracteres especiales, mayus, minu) -> Servicio

        //validar que la password ingresada sea la misma que se repite -> App pequenia Controlador

        //registrar usuario en la listaHarcodeada pero que no exista previamente el emailregistrado-> Servicio 
    }

    private boolean estanTodosLosCamposCompletos(UsuarioDto usuarioDto) {
        String emailIngresado = usuarioDto.getEmail();
        String contrasenia = usuarioDto.getContrasenia();
        String contraseniaRepetida = usuarioDto.getContraseniaRepetida();

        return false;
    }
}
