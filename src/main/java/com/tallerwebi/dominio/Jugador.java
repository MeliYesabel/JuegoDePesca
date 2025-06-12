package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
public class Jugador {

   // @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_jugador;
    private double monedas;
    private Integer cant_carnada;
    private List<Objeto> listaObjetos;

public Jugador(){
    this.monedas = 0.0;
    listaObjetos = new ArrayList<>();
    /*lo sett para probar*/
    cant_carnada = 3;

}

    public Integer getCant_carnada() {
    return cant_carnada;
    }

    public void setCant_carnada(Integer cant_carnada) {
    this.cant_carnada = cant_carnada;
    }

    public double getMonedas() {
    return monedas;
    }

    public void  setMonedas(double monedas) {
        this.monedas = monedas;
    }






    }



