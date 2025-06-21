package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String dios;
    private String imagenUrl;
    private Boolean desbloqueado;

    @OneToMany(mappedBy = "mar", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pez> peces = new ArrayList<>();

    private Double costoDeDesbloqueo;

    public Mar(String nombre) { //Creado momentanemente para poder seguir con el test
        this.nombre = nombre;
    }

    public Mar() {

    }

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
