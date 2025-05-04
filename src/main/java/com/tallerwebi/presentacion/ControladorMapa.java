package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorMapa {

    @RequestMapping("/mapa")
    public ModelAndView irMapa(){
        ModelMap model = new ModelMap();
        model.put("marUno","Mitología Griega");
        model.put("marDos","Mitología Nórdica");
        model.put("marTres","Mitología Japonesa");
        model.put("marCuatro","Mitología Yoruba");
        model.put("marCinco","Mitología Indú");
        model.put("marSeis","Mitología Asteca");
        model.put("marSiete","Mitología China");
        return new ModelAndView("vistaMapa", model);
    }
}
