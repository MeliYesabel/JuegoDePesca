package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ParametroInvalidoException;

import java.util.Collections;
import java.util.List;

public class ServicioTurnoImpl implements ServicioTurno {
    @Override
    public Turno crearUnTurno(Run run) {

        if (run.getCebo() <= 0){
            throw new ParametroInvalidoException("No hay cebo para crear un turno.");
        }

       return new Turno();
    }

    @Override
    public List<Pez> tomarTresPecesParaElTurno(Run run) {

        List<Pez> todosLosPecesDelMar = run.getMar().getPeces();

        Collections.shuffle(todosLosPecesDelMar); // se mesclan

        return todosLosPecesDelMar.subList(0, 3);
    }


}
