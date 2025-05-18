package com.tallerwebi.dominio.excepcion;

public class Objeto {
    private Double precioObjeto;
    private Integer idObjeto;
    private Boolean estaComprado = false;

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
