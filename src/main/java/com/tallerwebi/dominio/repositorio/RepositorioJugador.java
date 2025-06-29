package com.tallerwebi.dominio.repositorio;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Objeto;

import java.util.List;

public interface RepositorioJugador {
    Jugador buscarJugador(Long idJugador);
    void guardarJugador(Jugador jugador);
    List<Objeto> obtenerListaDeObjetosDelJugador(Jugador jugador);
    Jugador buscarjugadorPorId(Long idJugador);

}

