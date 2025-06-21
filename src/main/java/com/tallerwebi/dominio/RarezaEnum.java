package com.tallerwebi.dominio;
public enum RarezaEnum {
    COMUN(0.7, 10),
    RARO(0.35, 25),
    LEGENDARIO(0.15, 100);

    private final double probabilidadAtrapar;
    private final int valor;

    RarezaEnum(double probabilidadAtrapar, int valor) {
        this.probabilidadAtrapar = probabilidadAtrapar;
        this.valor = valor;
    }

    public double getProbabilidadAtrapar() {
        return probabilidadAtrapar;
    }

    public int getValor() {
        return valor;
    }

}
