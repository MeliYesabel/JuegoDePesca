package com.tallerwebi.presentacion;
import com.tallerwebi.dominio.DatosColeccion;
import com.tallerwebi.dominio.ServicioColecciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class ControladorLibroDeColeccion {

    private ServicioColecciones servicioColecciones;

    @Autowired
    public ControladorLibroDeColeccion(ServicioColecciones servicioColecciones){
        this.servicioColecciones = servicioColecciones;
    }

    @RequestMapping("/colecciones")
    public ModelAndView irColecciones(){
        ModelMap model = new ModelMap();
        model.put("mensaje","Estas en el libro de colecciones");
        return new ModelAndView("vistaColecciones.html", model);
    }

    @RequestMapping("/misColecciones")
    public ModelAndView irALibroDeColecciones(){
        DatosColeccion datos = servicioColecciones.obtenerDatosColeccion("pez comun","3 kg","Epico", 12000 ,20);
         return new ModelAndView("vistaLibroDeColecciones", "datosColeccion", datos);
    }

    @RequestMapping("/filtrarColeccion")
    public ModelAndView irFiltrarColeccion(){
        ModelMap model = new ModelMap();
        model.put("filtrar","Coleccion de peces filtradas");
        return new ModelAndView("vistaFiltrarColeccion.html", model);
    }

    @RequestMapping("/ordenarColeccion")
    public ModelAndView irOrdenarColeccion(){
        ModelMap model = new ModelMap();
        model.put("ordenar","Coleccion de peces ordenadas");
        return new ModelAndView("vistaOrdenarColeccion.html", model);
    }

}

