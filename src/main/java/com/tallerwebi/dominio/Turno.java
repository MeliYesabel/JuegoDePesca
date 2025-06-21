package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.List;
@Entity
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Run run;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Pez> pecesGenerados;
    @ManyToOne(fetch = FetchType.LAZY)
    private Pez seleccionado;
    private Boolean fuePescado;
    private Integer ceboRestante;
    private Integer monedasGanadas;

    public Turno() {}


    public void setPecesGenerados(List<Pez> pecesGenerados) {
        this.pecesGenerados = pecesGenerados;
    }

    public Integer getMonedasGanadas() {
        return monedasGanadas;
    }

    public void setMonedasGanadas(Integer monedasGanadas) {
        this.monedasGanadas = monedasGanadas;
    }

    public Integer getCeboRestante() {
        return ceboRestante;
    }

    public void setCeboRestante(Integer ceboRestante) {
        this.ceboRestante = ceboRestante;
    }

    public Boolean getFuePescado() {
        return fuePescado;
    }

    public void setFuePescado(Boolean fuePescado) {
        this.fuePescado = fuePescado;
    }

    public Pez getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Pez seleccionado) {
        this.seleccionado = seleccionado;
    }

    public List<Pez> getPecesGenerados() {
        return pecesGenerados;
    }
}
