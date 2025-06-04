package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.Objeto;

import java.util.List;

public interface ServicioTurno {
    Turno crearUnTurno(Run run);
    List<Pez> tomarTresPecesParaElTurno(Run run);

}
