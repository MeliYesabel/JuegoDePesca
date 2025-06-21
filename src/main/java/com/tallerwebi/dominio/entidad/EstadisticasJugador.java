package com.tallerwebi.dominio.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EstadisticasJugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalPecesAtrapados;
    private int pecesComunes;
    private int pecesRaros;
    private int pecesLegendarios;
    private int vecesJugadas;
    private int monedasGastadas;
    private int monedasGanadas;
}
