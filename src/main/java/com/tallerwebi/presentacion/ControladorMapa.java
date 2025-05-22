package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorMapa {

    /*mas adelante agregar las vistas de los distintos mares*/

    @RequestMapping("/vistaMapa")
    public ModelAndView vistaMapa() {
        return new ModelAndView("vistaMapa");
    }

    @RequestMapping("/vistaTienda")
    public ModelAndView irAVistaTienda(){
        return new ModelAndView("vistaTienda");
    }


    @RequestMapping("/vistaLogros")
    public ModelAndView irAVistaLogros(){
        return new ModelAndView("vistaLogros");
    }

    /*si debe buscar los datos de otro clase de deberia llamar a la clase y dspues usarlo el controllador para que el asigne
    * como va a ser destinado. A menos que el controller no tenga un alogica muy completo no va a ser necesario usar
    * de un servicio(no todos los test van a necesitar e estos)*/


    @RequestMapping("/vistaMarDesbloqueado")
    public ModelAndView vistaMarDesbloqueado() {
        return new ModelAndView("vistaMarDesbloqueado");
    }
    @RequestMapping("/vistaSeleccion")
    public ModelAndView irAVistaSeleccion() {
        return new ModelAndView("vistaSeleccion");
    }


}
