package com.tallerwebi.dominio;

import javax.persistence.*;

@Entity
public class Pez {
    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rareza rareza; //rareza entidad opcion 1

    //opcion 2 rareza enum y no se crea tabla en la bbdd solo un campo con el nombre de la rareza
//    @Enumerated(EnumType.STRING)
//    private RarezaEnum rarezaEnum;




    @ManyToOne(fetch = FetchType.LAZY)
    private Mar mar;

    private  String imagenUrl;

    private boolean atrapado; //para la coleccion de peces creo q es necesario para q se vea desbloqueado o bloqueado

    //Para vender el pez en caso si sigue la idea de que la tienda venda y compre peces
    private Double valorVenta;
    private String descripcion;

    public Pez(String nombre) {
        this.nombre = nombre;
    }
    public Pez(String nombre, Rareza rareza) {
        this.nombre = nombre;
        this.rareza = rareza;
    }

    public Pez() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Rareza getRareza() {
        return rareza;
    }
    public void setRareza(Rareza rareza) {
        this.rareza = rareza;
    }

    public Mar getMar() {
        return mar;
    }
    public void setMar(Mar mar) {
        this.mar = mar;
    }

    @Override
    public String toString() {
        return "Pez{" +
                "nombre='" + nombre + '\'' +
                ", rareza=" + rareza +
                '}';
    }

}
