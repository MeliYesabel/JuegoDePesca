package com.tallerwebi.dominio.servicio;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.repositorio.RepositorioJugador;
import com.tallerwebi.dominio.repositorio.RepositorioObjeto;

public interface ServicioJugador {
    Jugador buscarJugadorPorId(Long idJugador);
    Boolean equipaCaniaAPersonaje(Jugador jugador, Long idObjeto);

    Jugador inicializarJugador();
    RepositorioJugador getRepositorioJugador();
    RepositorioObjeto getRepositorioObjeto();
     Integer obtenerCantDeCebos(Jugador jugador);
     void guardar(Jugador jugador);




}
