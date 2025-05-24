package com.tallerwebi.dominio;

public class Rareza {

    private String nombre;
    private Double probabilidadAtrapar;
    private Integer valor;

    public Rareza(String nombre, Double probabilidadAtrapar, Integer valor) {
        this.nombre = nombre;
        this.probabilidadAtrapar = probabilidadAtrapar;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getProbabilidadAtrapar() {
        return probabilidadAtrapar;
    }

    public void setProbabilidadAtrapar(Double probabilidadAtrapar) {
        this.probabilidadAtrapar = probabilidadAtrapar;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public double getProbabilidadAtrapado() {
        return probabilidadAtrapar;
    }
}