package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.Objeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ControladorTienda {

    private ServicioTienda servicioTienda;

    @Autowired
    public ControladorTienda(ServicioTienda servicioTienda) {

        this.servicioTienda = servicioTienda;
    }

    @RequestMapping("/inicio")
    public ModelAndView iniciarSesion(HttpSession session) {
        Jugador jugador = new Jugador();
       // jugador.setNombre("Jugador de prueba");
        jugador.setMonedas(200.0);
        session.setAttribute("jugador", jugador);
        return new ModelAndView("redirect:/tienda");
    }

    @RequestMapping("/tienda")
    public ModelAndView irTienda(HttpSession session) {
        ModelMap model = new ModelMap();
        if(servicioTienda.getListaObjetos().isEmpty()){
            servicioTienda.agregarObjetoDisponible(new Objeto(1, 100.0));
            servicioTienda.agregarObjetoDisponible(new Objeto(2, 150.0));
        }
        Jugador jugador = (Jugador) session.getAttribute("jugador");
        model.put("claveTienda","Esta es la tienda");
        model.put("jugador", jugador);
        model.put("objetosDisponibles", servicioTienda.getListaObjetos());
        // También deberías mostrar los objetos en pantalla


       /* Jugador jugador = (Jugador) session.getAttribute("jugador");
        if (jugador != null) {
            model.put("jugador", jugador);
        }*/


        return new ModelAndView("vistaTienda.html", model);
    }

   /* @RequestMapping("/comprarObjeto")
    public ModelAndView comprarObjeto(Jugador jugador, Integer idObjeto) {

      if(servicioTienda.comprarObjeto(jugador,idObjeto)) {
          return new ModelAndView("vistaObjeto.html");
      }

      return new ModelAndView("vistaTienda.html");

    }*/

    @RequestMapping(value = "/comprarObjeto", method = RequestMethod.POST)
    public ModelAndView comprarObjeto(HttpSession session,@RequestParam Integer idObjeto) {
        ModelMap model = new ModelMap();

        // Recuperás el jugador desde la sesión (que ya debería estar guardado)
        Jugador jugador = (Jugador) session.getAttribute("jugador");

        if(jugador == null){
            model.put("error","No hay sesiones activas para este jugador");
            return new ModelAndView("vistaTienda.html", model);
        }

        try {
            servicioTienda.comprarObjeto(jugador, idObjeto);
            model.put("mensaje", "¡Compra realizada con éxito!");
            model.put("jugador", jugador);  // para actualizar info
            return new ModelAndView("vistaObjeto.html", model);

        } catch (ParametroInvalidoException | ObjetoInexistenteException | MonedasInsuficientesException e) {
            model.put("error", e.getMessage());
            model.put("jugador", jugador);
            return new ModelAndView("vistaCompraSinExito.html", model);
        }

       /* model.put("jugador",jugador);
        model.put("objetosDisponibles", servicioTienda.getListaObjetos());
          //  model.put("jugador", jugador); // para mantener info */


    }



}
