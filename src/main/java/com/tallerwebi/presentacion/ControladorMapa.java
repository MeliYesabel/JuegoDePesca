package com.tallerwebi.presentacion;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorMapa {

    @RequestMapping("/mapa")//no me deja entrar a team jaaj dios mio 
    public ModelAndView irMapa(HttpServletRequest request){
        ModelMap model = new ModelMap();
        model.put("marUno","Mitología Griega");
        model.put("marDos","Mitología Nórdica");
        model.put("marTres","Mitología Japonesa");
        model.put("marCuatro","Mitología Yoruba");
        model.put("marCinco","Mitología Indú");
        model.put("marSeis","Mitología Asteca");
        model.put("marSiete","Mitología China");
        String username = request.getSession().getAttribute("username").toString(); //

        model.put("username", username); //ahh ya entendi
        return new ModelAndView("vistaMapa", model);
    }
}
