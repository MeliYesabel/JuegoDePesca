package com.tallerwebi.dominio.entidad;

import javax.persistence.*;

@Entity
public class Run {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cebo;
    @ManyToOne
    private Mar mar;
   // @OneToMany
    //private List<Pez> pecesPescados;
   @ManyToOne
   private Jugador jugador;
    //@OneToMany
    //private List<Turno> historialTurnos;

    public Run() {
    }

    public Run(Mar mar, Integer cebo) {
        this.mar = mar;
        this.cebo = cebo;
    }

    public Integer getCebo() {
        return cebo;
    }
    public void setCebo(Integer cebo) {
        this.cebo = cebo;
    }
    public Mar getMar() {
        return mar;
    }
    public void setMar(Mar mar) {
        this.mar = mar;
    }
   /* public void setPecesPescados(List<Pez> pecesPescados) {
        this.pecesPescados = pecesPescados;
    }
    public void setHistorialTurnos(List<Turno> historialTurnos) {
        this.historialTurnos = historialTurnos;
    }

    public Integer getCeboRestante() {
        return cebo;
    }*/

    public void restarCebo() {
        if (cebo == 0) {
            throw new IllegalStateException("Se acabo la pesca, no hay mas cebos disponibles.");
        } else {
            cebo--;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}
