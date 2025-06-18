package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_jugador;
    private String nombre;
    private String alias;
    private double monedas;
    private Integer cant_carnada;

    @OneToMany
   private List<Objeto> listaObjetos;

    @ManyToMany
    private List<Mar> mares;


    public Jugador(){
    this.monedas = 0.0;
    listaObjetos = new ArrayList<>();
    }

    public Jugador(String nombre, String alias, double monedas, Integer cant_carnada){
        this.nombre = nombre;
        this.alias = alias;
        this.monedas = monedas;
        this.cant_carnada = cant_carnada;
        listaObjetos = new ArrayList<>();
    }

    public List<Mar>getMares() {
        return mares;
    }

    public Long getId_jugador() {
    return id_jugador;
    }

    public void setId_jugador(Long id_jugador) {
    this.id_jugador = id_jugador;
    }
    public String getNombre() {
    return nombre;
    }
    public void setNombre(String nombre) {
    this.nombre = nombre;
    }
    public String getAlias() {
    return alias;
    }
    public void setAlias(String alias) {
    this.alias = alias;
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



