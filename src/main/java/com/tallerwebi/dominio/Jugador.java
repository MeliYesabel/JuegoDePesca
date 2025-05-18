package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.Objeto;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
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



      /*  public Objeto buscarObjeto(Integer idObjeto) {
            for (Objeto objeto : listaObjetos) {
                if (objeto.getIdObjeto() == idObjeto) {
                    return objeto;
                }
            }
            return null;
        }*/


    }



