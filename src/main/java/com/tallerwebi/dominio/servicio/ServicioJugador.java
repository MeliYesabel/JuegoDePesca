package com.tallerwebi.dominio.servicio;

import com.tallerwebi.dominio.entidad.Jugador;

public interface ServicioJugador {
    Jugador buscarJugadorPorId(Long idJugador);
    Boolean equipaCaniaAPersonaje(Jugador jugador, Long idObjeto);

    Jugador inicializarJugador();
}
