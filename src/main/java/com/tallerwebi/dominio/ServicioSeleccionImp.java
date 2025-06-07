package com.tallerwebi.dominio;

public class ServicioSeleccionImp implements ServicioSeleccion {

    @Override
    public Boolean laCantidadDeCarnadaEsMenorQueCindo(Integer cantCarnada) {
        if (cantCarnada <= 5 && cantCarnada > 0){
            return true;
        }
        return false;
    }
}
