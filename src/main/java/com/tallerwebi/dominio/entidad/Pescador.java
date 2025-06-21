package com.tallerwebi.dominio.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pescador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double modificadorProbabilidad;
    private String descripcionPasiva;
    private String imagenUrl;

    public Pescador() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getModificadorProbabilidad() {
        return modificadorProbabilidad;
    }

    public void setModificadorProbabilidad(Double modificadorProbabilidad) {
        this.modificadorProbabilidad = modificadorProbabilidad;
    }

    public String getDescripcionPasiva() {
        return descripcionPasiva;
    }

    public void setDescripcionPasiva(String descripcionPasiva) {
        this.descripcionPasiva = descripcionPasiva;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
