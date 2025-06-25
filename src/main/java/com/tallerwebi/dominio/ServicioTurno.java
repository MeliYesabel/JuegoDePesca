package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioTurno {
    Turno crearUnTurno(Run run);
    List<Pez> tomarTresPecesParaElTurno(Run run);
    List<Pez> guardarLosTresPecesParaElTurno(Run run);
    Pez seleccionarUnPez(List<Pez> pecesGenerados, Integer posicionDelPez);
    void restarCeboEquipado();
    List<Pez> obtenerTresPecesAleatorios();

    Pez pescarPezPorId(Long id);

    List<Pez> obtenerTresPecesDelMar(Long idMar);
}
