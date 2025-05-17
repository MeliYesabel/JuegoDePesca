package com.tallerwebi.dominio;

import java.util.List;

public class Turno {
    private List<Pez> pecesGenerados;
    private Pez seleccionado;
    private Boolean fuePescado;
    private Integer ceboRestante;
    private Integer monedasGanadas;

    public Turno(
            List<Pez> pecesGenerados, Integer ceboRestante, Integer monedasGanadas
    ) {
        this.pecesGenerados = pecesGenerados;
        this.ceboRestante = ceboRestante;
        this.monedasGanadas = monedasGanadas;
    }

    public Integer getCeboRestante() {
        return ceboRestante;
    }
}
