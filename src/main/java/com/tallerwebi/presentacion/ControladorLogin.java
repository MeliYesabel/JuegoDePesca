package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.UsuarioAuth;
import com.tallerwebi.dominio.excepcion.UsuarioInexistenteLoginException;
import com.tallerwebi.dominio.servicio.ServicioUsuarioAuthI;
import com.tallerwebi.presentacion.dto.UsuarioDto;
import com.tallerwebi.presentacion.dto.UsuarioSesionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ControladorLogin {

    private ServicioUsuarioAuthI servicioUsuarioAuthI;

    @Autowired
    public ControladorLogin(ServicioUsuarioAuthI servicioUsuarioI) {
        this.servicioUsuarioAuthI = servicioUsuarioI;
    }

    @GetMapping("login-pescador")
    public ModelAndView mostrarLogin() {
        ModelMap modeloLogin = new ModelMap();
        modeloLogin.put("usuarioDto", new UsuarioDto());
        return new ModelAndView("login-usuario", modeloLogin);
    }


    @PostMapping("/login-auth")
    public ModelAndView procesarLogin(@ModelAttribute("usuarioDto")UsuarioDto usuarioDto, HttpServletRequest request){
        ModelMap datosMapeados =new ModelMap();
        String emailIngresado = usuarioDto.getEmail();
        String contraseniaIngresada = usuarioDto.getContrasenia();

        if(usuarioDto.getEmail().isBlank()) datosMapeados.put("error_email_vacio", "El email es obligatorio");
        if(contraseniaIngresada.isBlank()) datosMapeados.put("error_password_vacia", "La contrasenia es obligatorio");

        if(!emailTieneUnFormatoValido(emailIngresado))
            datosMapeados.put("error_formato_email", "El formato del email es invalido");

        if(!usuarioDto.getEmail().isBlank() && !usuarioDto.getContrasenia().isBlank() && emailTieneUnFormatoValido(emailIngresado)){
            try {
                UsuarioAuth encontrado = this.servicioUsuarioAuthI.autenticar(emailIngresado, contraseniaIngresada);
                HttpSession sesion = request.getSession();
                String rol = encontrado.getTipoUsuario();

                //creo objeto dto seguro para la sesion
                UsuarioSesionDto usuarioSesion = new UsuarioSesionDto(encontrado.getId(), encontrado.getEmail(), rol);
                sesion.setAttribute("usuarioLogueado", usuarioSesion);

                if(rol.equalsIgnoreCase("ADMIN")){
                    return new ModelAndView("redirect:/admin/dashboard");
                }else{
                    return new ModelAndView("redirect:/mapa");
                }

            }catch (UsuarioInexistenteLoginException exception){
                datosMapeados.put("error_not_exist", "El usuario no se encuentraRegistrado");
                return new ModelAndView("login-usuario", datosMapeados);
            }
        }else {
            return new ModelAndView("login-usuario", datosMapeados);
        }

    }

    private boolean emailTieneUnFormatoValido(String emailIngresado) {
        return emailTieneUnArroba(emailIngresado) && emailTieneMinTresCharsAntesYDespuesDelArroba(emailIngresado);
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
