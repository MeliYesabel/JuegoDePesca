package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorMar {

    @GetMapping("/jugar")
    public ModelAndView mostrarEscenaDeJuego() {
        ModelMap datosModelado = new ModelMap();
        datosModelado.put("mensaje", "Que comience el juego (saw)!");//por el momento un msj aqui tiene que venir los objetos
        return new ModelAndView("mar-escena-de-juego", datosModelado);
    }
}
