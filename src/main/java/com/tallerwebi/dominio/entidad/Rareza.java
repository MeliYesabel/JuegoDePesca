package com.tallerwebi.dominio.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rareza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rareza;
    private String nombre;
    private Double probabilidadAtrapar;
    private Integer valor;

    /*para que el hibernate pueda settear despues los datos forma de conectarlo
    * a la base de datos */
    public Rareza() {

    }
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
