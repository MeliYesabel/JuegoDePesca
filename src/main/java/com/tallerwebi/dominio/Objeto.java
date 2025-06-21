package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Objeto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idObjeto;
    private Double precioObjeto;

    private String nombre;
    private Boolean estaComprado = false;

    @Column(nullable = true)
    private String imagenUrl
            ;
    @Column(nullable = true)
    private int cantidad;

    @Column(nullable = true) //para que sea opcional y el data.sql no rompa elproyecto
    private double bonificacionProbabilidad; //para jugar

    public Objeto(){

    }
    public Objeto( Double precioObjeto, String nombre) {
        this.precioObjeto = precioObjeto;

        this.nombre = nombre;
    }


    public String getNombre() {
        return nombre;
    }

    public Double getPrecioObjeto() {
        return precioObjeto;
    }

    public Integer getIdObjeto() {
        return idObjeto;
    }

    public void setPrecioObjeto(Double precioObjeto) {
        this.precioObjeto = precioObjeto;
    }

    public void setIdObjeto(Integer idObjeto) {
        this.idObjeto = idObjeto;
    }

    public Boolean getEstaComprado() {
        return estaComprado;
    }

    public void setEstaComprado(Boolean estaComprado) {
        this.estaComprado = estaComprado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Objeto objeto = (Objeto) o;
        return Objects.equals(idObjeto, objeto.idObjeto) && Objects.equals(precioObjeto, objeto.precioObjeto) && Objects.equals(nombre, objeto.nombre) && Objects.equals(estaComprado, objeto.estaComprado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idObjeto, precioObjeto, nombre, estaComprado);
    }
}
