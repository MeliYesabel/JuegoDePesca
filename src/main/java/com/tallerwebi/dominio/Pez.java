package com.tallerwebi.dominio;

public class Pez {
    private String nombre;
    private Rareza rareza;
    private Number precio;
    private Mar mar;

    public Pez(String nombre) {
        this.nombre = nombre;
    }
    public Pez(String nombre, Rareza rareza, Number precio) {
        this.nombre = nombre;
        this.rareza = rareza;
        this.precio = precio;
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
     public Number getPrecio() {
        return precio;
     }
     public void setPrecio(Number precio) {
        this.precio = precio;
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
                ", precio=" + precio +
                '}';
    }
}

