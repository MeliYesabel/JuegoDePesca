package com.tallerwebi.dominio.entidad;

import javax.persistence.*;

import java.util.List;

@Entity
public class Resumen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Pez> pecesPescados;
    private Integer totalGanado;
    private int turnosJugados;

    @ManyToOne(optional = true) // Si querés saber en qué mar fue
    private Mar mar;

    public Resumen() {}

    public Resumen(List<Pez> pecesPescados, Integer totalGanado, int turnosJugados) {
        this.pecesPescados = pecesPescados;
        this.totalGanado = totalGanado;
        this.turnosJugados = turnosJugados;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public List<Pez> getPecesPescados() {
        return pecesPescados;
    }

    public void setPecesPescados(List<Pez> pecesPescados) {
        this.pecesPescados = pecesPescados;
    }

    public Integer getTotalGanado() {
        return totalGanado;
    }

    public void setTotalGanado(Integer totalGanado) {
        this.totalGanado = totalGanado;
    }

    public int getTurnosJugados() {
        return turnosJugados;
    }

    public void setTurnosJugados(int turnosJugados) {
        this.turnosJugados = turnosJugados;
    }

    public Mar getMar() {
        return mar;
    }

    public void setMar(Mar mar) {
        this.mar = mar;
    }
}
