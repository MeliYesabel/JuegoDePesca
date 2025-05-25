package com.tallerwebi.dominio;

import java.util.List;

public class Mar {
    private String nombre;
    private List<Pez> peces;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<Pez> getPeces() {
        return peces;
    }
    public void setPeces(List<Pez> peces) {
        this.peces = peces;
    }
    public void agregarPezAlMar(Pez pez) {
        peces.add(pez);
    }

}
