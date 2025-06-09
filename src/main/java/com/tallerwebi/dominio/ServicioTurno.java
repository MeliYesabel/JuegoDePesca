package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioTurno {
    Turno crearUnTurno(Run run);
    List<Pez> tomarTresPecesParaElTurno(Run run);

}
