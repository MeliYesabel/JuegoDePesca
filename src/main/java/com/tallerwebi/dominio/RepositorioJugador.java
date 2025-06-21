package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioJugador {
    Jugador buscarJugador(Long idJugador);
    void guardarJugador(Jugador jugador);
    List<Objeto> obtenerListaDeObjetosDelJugador(Jugador jugador);
    Jugador buscarjugadorPorId(Long idJugador);


}

