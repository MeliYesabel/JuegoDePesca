package com.tallerwebi.dominio;

public interface RepositorioJugador {
    Jugador buscarJugador(Long idJugador);
    void guardarJugador(Jugador jugador);
}

