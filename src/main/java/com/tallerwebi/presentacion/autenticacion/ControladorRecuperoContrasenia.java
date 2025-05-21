package com.tallerwebi.presentacion.autenticacion;

import com.tallerwebi.dominio.autenticacion.ServicioUsuarioI;
import com.tallerwebi.dominio.autenticacion.UsuarioAuth;
import com.tallerwebi.dominio.excepcion.UsuarioInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRecuperoContrasenia {

    private ServicioUsuarioI servicioUsuarioI;

    @Autowired
    public ControladorRecuperoContrasenia(ServicioUsuarioI servicioUsuarioI) {
        this.servicioUsuarioI = servicioUsuarioI;
    }

    @GetMapping("/recuperar-contrasenia")
    public ModelAndView mostrarFormRecuperoContrasenia() {
        /*ModelMap datosMap = new ModelMap();
        datosMap.put("usuarioDto", new UsuarioDto());*/
        return new ModelAndView("recuperar-contrasenia");
    }

    @PostMapping("/recuperar-contrasenia")
    public ModelAndView recuperarContrasenia(@RequestParam(name = "email") String email) {
        ModelMap modelo = new ModelMap();
        if (email.isBlank())
            modelo.put("email_vacio", "El email es obligatorio");
        if (!emailTieneUnFormatoValido(email))
            modelo.put("email_invalido", "El formato del email es invalido");

        if (!email.isBlank() && emailTieneUnFormatoValido(email)) {
            try {
                servicioUsuarioI.buscarUsuarioPorEmail(email);
                modelo.put("usuarioDto", new UsuarioDto());
                modelo.put("mensaje", "Se le envio un enlace de recuperacion a tu email.");
                return new ModelAndView("login-pescador", modelo);

            } catch (UsuarioInexistenteException excepcion) {
                modelo.put("no_existe", "El email ingresado no se encuentra registrado.");
                return new ModelAndView("recuperar-contrasenia", modelo);
            }

        } else {
            return new ModelAndView("recuperar-contrasenia", modelo);
        }

    }

    private boolean emailTieneUnFormatoValido(String email) {
        return emailTieneUnArroba(email) && emailTieneMinTresCharsAntesYDespuesDelArroba(email);
    }

    private boolean emailTieneMinTresCharsAntesYDespuesDelArroba(String email) {
        String[] partesMail = email.split("\\@");

        return partesMail.length == 2 &&
                partesMail[0].length() >= 3 && partesMail[1].length() >= 3;
    }

    private boolean emailTieneUnArroba(String email) {
        int cont = 0;

        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@')
                cont++;
        }
        return cont == 1;
    }

}
