package com.tallerwebi.dominio.entidad;

import javax.persistence.*;

@Entity

public class Pez {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pez;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "id_rareza")  // <- esta parte es clave
    private Rareza rareza;


    /*segun la explicacion del prof es mejor tener manyto one q onetoone*/
    @ManyToOne
    @JoinColumn(name = "id_mar")
    private Mar mar;

    /*si o si se necesita un costructor vacio para poder crear instancias
    * y luego hiberate carga los datos desde a base de datos para luego inyectar
    * los valores setter. OJO eso no quiere decri que invalide a los demas*/
   // public Pez() {}
    public Pez() { }
    public Pez(String nombre) {
        this.nombre = nombre;
    }

    public Pez(String nombre, Rareza rareza) {
        this.nombre = nombre;
        this.rareza = rareza;
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

    public Long getId_pez() {
        return id_pez;
    }

    public void setId_pez(Long id_pez) {
        this.id_pez = id_pez;
    }
}
