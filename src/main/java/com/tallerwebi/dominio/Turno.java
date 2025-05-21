package com.tallerwebi.dominio;

import java.util.List;

public class Turno {
    private List<Pez> pecesGenerados;
    private Pez seleccionado;
    private Boolean fuePescado;
    private Integer ceboRestante;
    private Integer monedasGanadas;

    public Turno() {}


    public void setPecesGenerados(List<Pez> pecesGenerados) {
        this.pecesGenerados = pecesGenerados;
    }
}
