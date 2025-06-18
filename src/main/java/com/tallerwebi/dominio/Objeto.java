package com.tallerwebi.dominio;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Objeto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Double precioObjeto;
    private Integer idObjeto;
    private Boolean estaComprado = false;

    public Objeto() {}

    public Objeto(Integer idObjeto, Double precioObjeto) {
        this.precioObjeto = precioObjeto;
        this.idObjeto = idObjeto;
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
}
