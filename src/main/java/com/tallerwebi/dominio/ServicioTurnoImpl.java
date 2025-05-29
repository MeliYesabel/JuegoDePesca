package com.tallerwebi.dominio;

public class ServicioTurnoImpl implements ServicioTurno {
    @Override
    public Turno crearUnTurno(Run run) {

        if (run.getCebo() <= 0){
            throw new ParametroInvalidoException("No hay cebo para crear un turno.");
        }

       return new Turno();
    }
}
