package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.Objeto;
import com.tallerwebi.dominio.excepcion.MonedasInsuficientesException;
import com.tallerwebi.dominio.excepcion.ObjetoInexistenteException;
import com.tallerwebi.dominio.excepcion.ObjetoYaCompradoException;
import com.tallerwebi.dominio.excepcion.ParametroInvalidoException;
import com.tallerwebi.dominio.RepositorioObjeto;
import com.tallerwebi.infraestructura.RepositorioJugadorImpl;
import com.tallerwebi.presentacion.dto.UsuarioSesionDto;
import org.hibernate.SessionFactory;
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

        UsuarioSesionDto usuarioSesion = (UsuarioSesionDto) session.getAttribute("usuarioLogueado");
        Jugador jugador = servicioJugador.buscarJugadorPorId(usuarioSesion.getId());

        if (jugador == null) {
            model.put("error", "No hay sesiones activas para este jugador");
            return new ModelAndView("vistaTienda.html", model);
        }

        model.put("claveTienda","Esta es la tienda");
        model.put("jugador", jugador);
        model.put("objetosDisponibles", servicioTienda.getListaObjetos());



        return new ModelAndView("vistaTienda.html", model);//no poner html al final
    }



    @RequestMapping(value = "/comprarObjeto", method = RequestMethod.POST)
    public ModelAndView comprarObjeto(HttpSession session,@RequestParam Long idObjeto) {
        ModelMap model = new ModelMap();

        // Recupero el jugador desde la sesión (que ya debería estar guardado)
        //Jugador jugador = (Jugador) session.getAttribute("jugador");
        UsuarioSesionDto usuarioSesion = (UsuarioSesionDto) session.getAttribute("usuarioLogueado");
        Jugador jugador = servicioJugador.buscarJugadorPorId(usuarioSesion.getId());

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



}
