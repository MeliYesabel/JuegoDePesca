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
private List<Objeto> objetosComprados = new ArrayList<>();;


public Jugador(String nombre){
    this.monedas = 0.0;
    //objetosComprados = new ArrayList<>(); preuguntar porque fallaba si la inicializo en el constructor
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

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Objeto> getObjetosComprados() {
        return objetosComprados;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setObjetosComprados(List<Objeto> objetosComprados) {
        this.objetosComprados = objetosComprados;
    }

    public void agregarObjeto(Objeto objeto){
    objetosComprados.add(objeto); //esto no tendria que estar aca
   }


    }



