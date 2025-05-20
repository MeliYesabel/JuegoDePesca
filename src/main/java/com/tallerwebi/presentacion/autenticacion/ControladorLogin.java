package com.tallerwebi.presentacion.autenticacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tallerwebi.dominio.autenticacion.ServicioUsuarioI;
import com.tallerwebi.dominio.autenticacion.UsuarioAuth;
import com.tallerwebi.dominio.excepcion.UsuarioInexistenteException;

@Controller
public class ControladorLogin {
    private ServicioUsuarioI servicioUsuarioI;
    
    @Autowired
    public ControladorLogin(ServicioUsuarioI servicioUsuarioI) {
        this.servicioUsuarioI = servicioUsuarioI;
    }

    @GetMapping("/login-pescador")
    public ModelAndView mostrarLogIn() {
        ModelMap modeloLogin = new ModelMap();
        modeloLogin.put("usuarioDto", new UsuarioDto());
        return new ModelAndView("login-pescador", modeloLogin);
    }

    @PostMapping("/login-pescador")
    public ModelAndView procesarFormularioLogin(@ModelAttribute("usuarioDto")UsuarioDto usuarioDto){
        ModelMap datosMapeados = new ModelMap();
        String emailIngresado = usuarioDto.getEmail();
        String contraseniaIngresada = usuarioDto.getContrasenia();

        if(usuarioDto.getEmail().isBlank()) datosMapeados.put("error_email_vacio", "El email es obligatorio");

        if(contraseniaIngresada.isBlank()) datosMapeados.put("error_password_vacia", "La contraseña es obligatorio");
        
        if(!emailTieneUnFormatoValido(emailIngresado))
            datosMapeados.put("error_formato_email", "El formato del email es invalido");
        

        if(!usuarioDto.getEmail().isBlank() && !usuarioDto.getContrasenia().isBlank() && emailTieneUnFormatoValido(emailIngresado)){ 
            datosMapeados.put("usuarioDto", usuarioDto);

            try{
                UsuarioAuth usuarioBuscado = servicioUsuarioI.buscarUsuario(emailIngresado, contraseniaIngresada);
                datosMapeados.put("usuarioLogueado", usuarioBuscado);

                return new ModelAndView("vistaMapa", datosMapeados);
            }catch(UsuarioInexistenteException excepcion){
                datosMapeados.put("error_not_exist", "El email ingresado no se encuentra registrado.");
                return new ModelAndView("login-pescador",datosMapeados); 
            }
        }else{
            return new ModelAndView("login-pescador", datosMapeados);
        }
    }





    private boolean emailTieneUnFormatoValido(String emailIngresado) {
        return(emailTieneUnArroba(emailIngresado) && emailTieneMinTresCharsAntesYDespuesDelArroba(emailIngresado))? true: false;
    }

    private boolean emailTieneMinTresCharsAntesYDespuesDelArroba(String email) {
        String[] partesMail = email.split("\\@");

        if(partesMail.length== 2 && 
        partesMail[0].length()>=3 && partesMail[1].length()>=3) return true;

        return false;
    }
    private boolean emailTieneUnArroba(String email) {
        int cont=0;

        for(int i=0; i<email.length(); i++){
            if(email.charAt(i)=='@')
                cont++;
        }
        return cont==1;
    }
}
