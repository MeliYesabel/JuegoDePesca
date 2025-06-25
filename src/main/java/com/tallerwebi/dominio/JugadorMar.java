package com.tallerwebi.dominio;

import javax.persistence.*;

@Entity
public class JugadorMar {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_jugador_mar;
    @ManyToOne
    @JoinColumn(name = "id_jugador")
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "id_mar")
    private Mar mar;
    private boolean estadoBloqueado;

    public JugadorMar(Jugador jugador, Mar mar, boolean estadoBloqueado) {
        this.jugador = jugador;
        this.mar = mar;
        this.estadoBloqueado = estadoBloqueado;
    }

    public JugadorMar() {

    }

    public Jugador getJugador() {
        return jugador;
    }
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    public Mar getMar() {
        return mar;
    }
    public void setMar(Mar mar) {
        this.mar = mar;
    }
    public boolean getEstadoBloqueado() {
        return estadoBloqueado;
    }
    public void setEstadoBloqueado(boolean estadoBloqueado) {
        this.estadoBloqueado = estadoBloqueado;
    }

    public void setId_jugador_mar(Long idJugadorMar) {
        this.id_jugador_mar = idJugadorMar;
    }

    public Long getId_jugador_mar() {
        return id_jugador_mar;
    }
}
