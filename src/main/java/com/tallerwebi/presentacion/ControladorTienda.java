package com.tallerwebi.presentacion;


import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.excepcion.MonedasInsuficientesException;
import com.tallerwebi.dominio.excepcion.ObjetoInexistenteException;
import com.tallerwebi.dominio.excepcion.ObjetoYaCompradoException;
import com.tallerwebi.dominio.excepcion.ParametroInvalidoException;
import com.tallerwebi.dominio.servicio.ServicioJugador;
import com.tallerwebi.dominio.servicio.ServicioTienda;
import com.tallerwebi.presentacion.dto.UsuarioSesionDto;
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
    private ServicioJugador servicioJugador;
   // private RepositorioObjeto repositorioObjeto;

    /*@Autowired
    RepositorioJugador repositorioJugador;
    @Autowired
    SessionFactory sessionFactory;*/

    @Autowired
    public ControladorTienda(ServicioTienda servicioTienda,ServicioJugador servicioJugador) {
        this.servicioTienda = servicioTienda;

        this.servicioJugador = servicioJugador;

       // this.repositorioJugador = new RepositorioJugadorImpl(); //lo agregue ahora
    }



    @RequestMapping("/tienda")
    public ModelAndView irTienda(HttpSession session) {
        ModelMap model = new ModelMap();



       servicioTienda.inicializarTienda();

       // Jugador jugador = (Jugador) session.getAttribute("jugador");

        Long idUsuarioLogueado =(Long)  session.getAttribute("idUsuarioLogueado");
        Jugador jugador = servicioJugador.buscarJugadorPorId(idUsuarioLogueado);

        if (jugador == null) {
            model.put("error", "No hay sesiones activas para este jugador");
            return new ModelAndView("vistaTienda.html", model);
        }

        model.put("claveTienda","Esta es la tienda");
        model.put("jugador", jugador);
        model.put("objetosDisponibles", servicioTienda.getListaObjetos());
        model.put("puedeReclamar", servicioTienda.puedeReclamarMonedas(jugador));



        return new ModelAndView("vistaTienda.html", model);//no poner html al final
    }



    @RequestMapping(value = "/comprarObjeto", method = RequestMethod.POST)
    public ModelAndView comprarObjeto(HttpSession session,@RequestParam Long idObjeto) {
        ModelMap model = new ModelMap();

        // Recupero el jugador desde la sesión (que ya debería estar guardado)
        //Jugador jugador = (Jugador) session.getAttribute("jugador");
        Long idUsuarioLogueado =(Long)  session.getAttribute("idUsuarioLogueado");
        Jugador jugador = servicioJugador.buscarJugadorPorId(idUsuarioLogueado);

        if(jugador == null){
            model.put("error","No hay sesiones activas para este jugador");
            return new ModelAndView("vistaTienda.html", model);// en vez de dirigirte a tienda que lo haga a login
        }

        try {
            servicioTienda.comprarObjeto(jugador, idObjeto);
            model.put("mensaje", "¡Compra realizada con éxito!");
            model.put("jugador", jugador);  // para actualizar info
            return new ModelAndView("vistaObjeto.html", model);

        } catch (ParametroInvalidoException | ObjetoInexistenteException | MonedasInsuficientesException |
                 ObjetoYaCompradoException e) {
            model.put("error", e.getMessage());
            model.put("jugador", jugador);
            return new ModelAndView("vistaCompraSinExito.html", model);
        }




    }


    @RequestMapping(value = "/comprarCarnada", method = RequestMethod.POST)
    public ModelAndView comprarCarnada(HttpSession session,@RequestParam Integer cant_carnada) {
        ModelMap model = new ModelMap();

        // Recupero el jugador desde la sesión (que ya debería estar guardado)
        //Jugador jugador = (Jugador) session.getAttribute("jugador");
        Long idUsuarioLogueado =(Long)  session.getAttribute("idUsuarioLogueado");
        Jugador jugador = servicioJugador.buscarJugadorPorId(idUsuarioLogueado);

        if(jugador == null){
            model.put("error","No hay sesiones activas para este jugador");
            return new ModelAndView("vistaTienda.html", model);// en vez de dirigirte a tienda que lo haga a login
        }

        try {
           // servicioTienda.comprarObjeto(jugador, idObjeto);
            servicioTienda.comprarCarnada(jugador,cant_carnada);
            model.put("mensaje", "¡Carnada comprada Exitosamente!");
            model.put("jugador", jugador);  // para actualizar info
            return new ModelAndView("vistaCompraCarnadaExitosa.html", model);// vistaObjeto.html

        } catch (ParametroInvalidoException  | MonedasInsuficientesException  e) {
            model.put("error", e.getMessage());
            model.put("jugador", jugador);
            return new ModelAndView("vistaCompraCarnadaSinExitoso.html", model);//vistaCompraCarnadaSinExitoso vistaCompraSinExito.html
        }




    }

    @RequestMapping(value = "/reclamar-monedas", method = RequestMethod.POST)
public ModelAndView reclamarMonedas(HttpSession session) {
        Long idUsuarioLogueado =(Long)  session.getAttribute("idUsuarioLogueado");
        Jugador jugador = servicioJugador.buscarJugadorPorId(idUsuarioLogueado);
        ModelMap model = new ModelMap();
        if(servicioTienda.puedeReclamarMonedas(jugador)){
            servicioTienda.reclamarMonedas(idUsuarioLogueado);

            jugador = servicioJugador.buscarJugadorPorId(idUsuarioLogueado);
            model.put("mensajeReclamo","!Reclamaste tus monedas¡");
        } else{
            model.put("mensajeReclamo","Espera unos segundos");
        }

        model.put("jugador", jugador);
        model.put("puedeReclamar",servicioTienda.puedeReclamarMonedas(jugador));
        model.put("segundosRestantes",servicioTienda.segundosParaProximoReclamo(jugador));

        model.put("objetosDisponibles", servicioTienda.getListaObjetos());
        model.put("claveTienda", "Bienvenido a la tienda");

        return new ModelAndView("vistaTienda.html", model);
    }


}
