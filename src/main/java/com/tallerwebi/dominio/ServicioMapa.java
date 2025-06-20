package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioMapa {
    Boolean calcularSiSePuedeDesbloquearUnMar(String aliasJugador, Double monedas);

    Mar obtenerElEstadoDeUnMarPorNombre(String nombre);

    boolean obtenerElEstadoDelMarSegunELJugador(Mar mar, Jugador jugadorActual);

    List<Mar> obtenerTodaListaDeMares();
}
