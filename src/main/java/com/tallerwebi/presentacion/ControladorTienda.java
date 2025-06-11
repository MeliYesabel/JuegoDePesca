package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.Objeto;
import com.tallerwebi.infraestructura.RepositorioObjeto;
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
    private RepositorioObjeto repositorioObjeto;

    @Autowired
    public ControladorTienda(ServicioTienda servicioTienda,RepositorioObjeto repositorioObjeto) {
        this.servicioTienda = servicioTienda;
        this.repositorioObjeto = repositorioObjeto;
    }

    @RequestMapping("/inicio")
    public ModelAndView iniciarSesion(HttpSession session) {
        Jugador jugador = new Jugador();

        jugador.setMonedas(200.0);
        session.setAttribute("jugador", jugador);
        return new ModelAndView("redirect:/tienda");
    }

    @RequestMapping("/tienda")
    public ModelAndView irTienda(HttpSession session) {
        ModelMap model = new ModelMap();
        if(servicioTienda.getListaObjetos().isEmpty()){
           Objeto objeto1 = new Objeto( 100.0,"caña");
           Objeto objeto2 = new Objeto( 150.0,"caña");


            servicioTienda.agregarYGuardarObjeto(objeto1);
            servicioTienda.agregarYGuardarObjeto(objeto2);
           /* //aca los agrego a la base de datos para que tengan id
            repositorioObjeto.guardarObjeto(objeto1);
            repositorioObjeto.guardarObjeto(objeto2);

            //  aca los agrego al servicio
           servicioTienda.agregarObjetoDisponible(objeto1);
            servicioTienda.agregarObjetoDisponible(objeto2); */




        }
        Jugador jugador = (Jugador) session.getAttribute("jugador");
        model.put("claveTienda","Esta es la tienda");
        model.put("jugador", jugador);
        model.put("objetosDisponibles", servicioTienda.getListaObjetos());



        return new ModelAndView("vistaTienda.html", model);//no poner html al final
    }



    @RequestMapping(value = "/comprarObjeto", method = RequestMethod.POST)
    public ModelAndView comprarObjeto(HttpSession session,@RequestParam Integer idObjeto) {
        ModelMap model = new ModelMap();

        // Recupero el jugador desde la sesión (que ya debería estar guardado)
        Jugador jugador = (Jugador) session.getAttribute("jugador");

        if(jugador == null){
            model.put("error","No hay sesiones activas para este jugador");
            return new ModelAndView("vistaTienda.html", model);// en vez de dirigirte a tienda que lo haga a login
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




    }



}
