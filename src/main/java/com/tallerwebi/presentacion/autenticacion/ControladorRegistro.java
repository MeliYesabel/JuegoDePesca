package com.tallerwebi.presentacion.autenticacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tallerwebi.dominio.autenticacion.ServicioUsuarioI;
import com.tallerwebi.dominio.excepcion.ContraseniaInvalidaExcepcion;
import com.tallerwebi.dominio.excepcion.UserExistenteExcepcion;

@Controller
public class ControladorRegistro {

    ServicioUsuarioI servicioUsuarioI;

    @Autowired
    public ControladorRegistro(ServicioUsuarioI servicioUsuarioI) {
        this.servicioUsuarioI = servicioUsuarioI;
    }


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
            datosModelados.put("campos_vacios", "Todos los campos son obligatorios.Por favor complete los que faltan");
            return new ModelAndView("registro", datosModelados);         
        }

        if(!usuarioDto.getContrasenia().equals(usuarioDto.getContraseniaRepetida())){
            datosModelados.put("error_repeticion", "Las contraseñas no coinciden.");
            return new ModelAndView("registro", datosModelados);   
        }

        try {
            servicioUsuarioI.registrarUsuario(usuarioDto);
            datosModelados.put("mensaje", "El usuario se registro exitosamente.");
            datosModelados.put("usuarioDto", new UsuarioDto());

        } catch (ContraseniaInvalidaExcepcion excepcion) {

            datosModelados.put("error_password_invalid", "Contraseña invalida: debe ser de longitud de 8 caracteres o mas y contener letras minusculas, mayusculas y algun numero");
            return new ModelAndView("registro", datosModelados);

        }catch (UserExistenteExcepcion excepcion){
            datosModelados.put("error_usuario_unico", excepcion.getMessage());
            return new ModelAndView("registro", datosModelados); 
        }
        
        return new ModelAndView("login-pescador", datosModelados);
        
    }

    private boolean estanTodosLosCamposCompletos(UsuarioDto usuarioDto) {
        String emailIngresado = usuarioDto.getEmail();
        String contrasenia = usuarioDto.getContrasenia();
        String contraseniaRepetida = usuarioDto.getContraseniaRepetida();

        return !emailIngresado.isBlank() && !contrasenia.isBlank() && !contraseniaRepetida.isBlank();
        
    }

    //validar que todos los campos no esten vacios: "Todos los campos son obligatorios. 
        //Por favor complete los que faltan -> Controlador

        //validar password (longitud de 8 caracteres, tiene numero, caracteres especiales, mayus, minu) -> Servicio

        //validar que la password ingresada sea la misma que se repite -> App pequenia Controlador

        //registrar usuario en la listaHarcodeada pero que no exista previamente el emailregistrado-> Servicio 
  

}
