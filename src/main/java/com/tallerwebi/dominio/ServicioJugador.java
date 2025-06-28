package com.tallerwebi.dominio;

public interface ServicioJugador {
    Jugador buscarJugadorPorId(Long idJugador);

    Boolean equipaCaniaAPersonaje(Jugador jugador, Long idObjeto);

    Jugador inicializarJugador();
    RepositorioJugador getRepositorioJugador();
    RepositorioObjeto getRepositorioObjeto();
}
