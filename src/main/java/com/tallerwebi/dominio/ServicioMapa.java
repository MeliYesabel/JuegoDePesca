package com.tallerwebi.dominio;

public interface ServicioMapa {
    Boolean calcularSiSePuedeDesbloquearUnMar(String aliasJugador, Double monedas);

    Mar obtenerElEstadoDeUnMarPorNombre(String nombre);

    boolean obtenerElEstadoDelMarSegunELJugador(Mar mar, Jugador jugadorActual);
}
