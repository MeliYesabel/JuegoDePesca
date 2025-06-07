package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Mar;
import com.tallerwebi.dominio.MonedasInsuficientesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.tallerwebi.dominio.ServicioMapa;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorMapa {

    /*agrego la interfaz que conecta con los test de servicio(logica de negocio)*/
    private ServicioMapa servicioMapa;
    /*creo el constructor*/
    @Autowired
    public ControladorMapa(ServicioMapa servicioMapa) {
        this.servicioMapa = servicioMapa;
    }

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


    @RequestMapping("/vistaMarBloqueado")
    public ModelAndView vistaMarDesbloqueado() {
        return new ModelAndView("vistaMarBloqueado");
    }
    @RequestMapping("/vistaSeleccion")
    public ModelAndView irAVistaSeleccion() {
        return new ModelAndView("vistaSeleccion");
    }


    /*DUDA:  @RequestMapping("/vistaMarDesbloqueado") aca hiria esto */
    public ModelAndView redireccionDeVistasDependiendoDelUsuario(String aliasJugador, Double monedasJuntadas) {
        ModelMap modelMap = new ModelMap();
        Mar marFalse = new Mar("mar sett",0.0,"pueba",false );
        Mar marTrue = new Mar("mar sett",0.0,"pueba",true );

        if (monedasJuntadas == 0.0 ) {
            modelMap.put("mensajeDeVistaError", "El Usuario no cuenta con Monedas");
            return new ModelAndView("vistaMapa", modelMap);
        }

        /*agregue 31/05 ->
        * hasta que no cree una clase me va a dar null xq no esta llamando a uno
        * asi que por ahora lo sett para hacer los test  */
        if (marFalse.getEstadoBloqueado().equals(false)) {
            modelMap.put("mensajeDeVistaError", "El mar se encuentra en estado BLOQUEADO");
            return new ModelAndView("vistaMapa", modelMap);
        }else if (marTrue.getEstadoBloqueado().equals(true)) {
            return new ModelAndView("vistaSeleccion", modelMap);
        }

        /*agregue una exception -> try catch DUDA: esto deberia ir a vistaMapa o vistaMarBloqueado?*/
        try {
            servicioMapa.calcularSiSePuedeDesbloquearUnMar(aliasJugador, monedasJuntadas);
        }catch (MonedasInsuficientesException e) {
            modelMap.put("mensajeErrorMonedas", "El Usuario no tiene suficientes monedas para desbloquear el mar");
            return new ModelAndView("vistaMapa", modelMap);
        }


        return new ModelAndView("vistaSeleccion");
    }


}
