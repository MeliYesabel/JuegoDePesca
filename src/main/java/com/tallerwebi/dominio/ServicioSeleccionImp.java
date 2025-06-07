package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;

@Service
public class ServicioSeleccionImp implements ServicioSeleccion {

    @Override
    public Boolean laCantidadDeCarnadaEsMenorQueCindo(Integer cantCarnada) {
        if (cantCarnada <= 5 && cantCarnada > 0){
            return true;
        }
        return false;
    }
}
