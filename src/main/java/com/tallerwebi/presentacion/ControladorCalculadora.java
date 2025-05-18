package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorCalculadora {



   /* @RequestMapping("/calculadora")
    public ModelAndView calculadora(){
        int valorUno = 1;
        int valorDos = 2;
        ModelMap model = new ModelMap();

        String resultado = mostrarResultado(valorUno,valorDos,"suma");
        model.put("valorUno",valorUno);
        model.put("valorDos",valorDos);
        model.put("resultado",resultado);
        return new ModelAndView("calculadora",model);
    }*/
   @RequestMapping("/calculadora")
    public ModelAndView calculadora(){
        return new  ModelAndView("calculadora");
    }

    /*@RequestMapping("/resultadoCalculadora")
    public ModelAndView resultadoCalculadora(){
        return new  ModelAndView("resultadoCalculadora");
    }*/


   @RequestMapping(path ="/calcular", method = RequestMethod.GET)
    public ModelAndView calcular( @RequestParam("numeroUno") Integer numeroUno, @RequestParam ("numeroDos") Integer numeroDos, @RequestParam ("operacion") String operacion){
         double resultado = 0;
        ModelMap model = new ModelMap();
        model.put("numeroUno",numeroUno);
        model.put("numeroDos",numeroDos);
        model.put("operacion",operacion);
        /*model.put("resultado",resultado);*/
        if(operacion.equals("sumar")){
         resultado =  numeroUno + numeroDos;
            model.put("resultado",resultado);
        }
        if(operacion.equals("restar")){
            resultado = numeroUno - numeroDos;
            model.put("resultado",resultado);
        }
        if(operacion.equals("multiplicar")){
            resultado = numeroUno * numeroDos;
            model.put("resultado",resultado);
        }
        if(operacion.equals("dividir")){
            resultado = numeroUno / numeroDos;
            model.put("resultado",resultado);
        }
        return new ModelAndView("resultadoCalculadora",model);
       // resultado.toString();
    }
}
