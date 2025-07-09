package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Pez;
import com.tallerwebi.dominio.entidad.Run;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ControladorResumenRun {

        @RequestMapping("/resumen")
        public ModelAndView mostrarResumen(HttpSession session) {
            ModelMap model = new ModelMap();
            Run run = (Run) session.getAttribute("run");

            if (run == null) {
                model.put("error", "No hay una partida activa. Volvé al inicio.");
                return new ModelAndView("vistaResumen.html", model);
            }

            Integer ganancia = run.obtenerganacia();
            model.put("ganancia", ganancia != null ? ganancia : 0);

            List<Pez> pecesPescados = run.getPecesPescados();
            Integer cantidadPecesPescados = (pecesPescados != null) ? pecesPescados.size() : 0;
            model.put("cantidadPeces", cantidadPecesPescados);

            return new ModelAndView("vistaResumen", model);
        }
    }


