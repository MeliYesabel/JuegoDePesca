package com.tallerwebi.dominio.servicio;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioSeleccionImp implements ServicioSeleccion {

    @Override
    public Boolean laCantidadDeCarnadaEsMenorQueCindo(Integer cantCarnada) {
        if (cantCarnada != null && cantCarnada <= 5 && cantCarnada > 0){
            return true;
        }
        return false;
    }
}
