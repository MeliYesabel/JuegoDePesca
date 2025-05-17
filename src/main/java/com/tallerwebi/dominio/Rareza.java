package com.tallerwebi.dominio;

public enum Rareza {
    NORMAL(0.5, 10),
    RARO(0.3, 25),
    EPICO(0.05, 100); //modificable, lo puse por tener algo por el momento

    private final double probabilidadAtrapado;
    private final int valorBase;

    Rareza(double probabilidadAtrapado, int valorBase) {
        this.probabilidadAtrapado = probabilidadAtrapado;
        this.valorBase = valorBase;
    }

    public double getProbabilidadAtrapado() {
        return probabilidadAtrapado;
    }

    public int getValorBase() {
        return valorBase;
    }
}

