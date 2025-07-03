package com.tallerwebi.dominio.entidad;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Entity
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Pez> pecesGenerados;
    @ManyToOne
    @JoinColumn(name = "seleccionado_id_pez")
    private Pez seleccionado;
    private Boolean fuePescado;
    private Integer ceboRestante;
    private Integer monedasGanadas;

    /*relacion con turno
    @ManyToOne
    @JoinColumn(name = "run_id")
    private Run run;*/

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
