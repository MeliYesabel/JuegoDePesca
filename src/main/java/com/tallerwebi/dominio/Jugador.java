package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

private double monedas;
private String nombre;

@OneToMany
private List<Objeto> listaObjetos;


public Jugador(String nombre){
    this.monedas = 0.0;
    listaObjetos = new ArrayList<>();
    this.nombre = nombre;

}

    public Jugador() {

    }

    public double getMonedas() {
    return monedas;
    }

    public void  setMonedas(double monedas) {
        this.monedas = monedas;
    }






    }



