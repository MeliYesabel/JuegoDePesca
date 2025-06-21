package com.tallerwebi.dominio;

public interface ServicioJugador {


    Boolean equipaCaniaAPersonaje(Jugador jugador, Long idObjeto);

    Jugador inicializarJugador();
}
