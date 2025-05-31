package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.Objeto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
public class Jugador {

   // @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_jugador;
    private double monedas;
    private List<Objeto> listaObjetos;

public Jugador(){
    this.monedas = 0.0;
    listaObjetos = new ArrayList<>();

}

    public double getMonedas() {
    return monedas;
    }

    public void  setMonedas(double monedas) {
        this.monedas = monedas;
    }






    }



