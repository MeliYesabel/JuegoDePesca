package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**/
@Entity
public class Mar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mar;
    private String nombre;
    private Double precio;
    private String descripcion;
    private Boolean estadoBloqueado;/*esto deberia estar en la base de dat0s?*/
    /*ya que hicimoe @manyToOne en pez esto puede ser opcional*/
    @OneToMany
    private List<Pez> peces;

    @OneToMany(mappedBy = "mar")
    private List<JugadorMar>jugadores = new ArrayList<>();

    public Mar() {}

    /*Solo para probar los test */
    public Mar(String nombre, Double precio, String descripcion, Boolean estadoBloqueado) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.estadoBloqueado = estadoBloqueado;
        /*creo el array*/
        jugadores = new ArrayList<>();
    }

    public Long getId_mar() {
        return id_mar;
    }
    public void setId_mar(Long id_mar) {
        this.id_mar = id_mar;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Boolean getEstadoBloqueado() {
        return estadoBloqueado;
    }
    public void setEstadoBloqueado(Boolean estadoBloqueado) {
        this.estadoBloqueado = estadoBloqueado;
    }

    public List<Pez> getPeces() {
        return peces;
    }

}
