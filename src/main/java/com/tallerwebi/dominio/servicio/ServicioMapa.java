package com.tallerwebi.dominio.servicio;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Mar;

import java.util.List;

public interface ServicioMapa {
    Boolean calcularSiSePuedeDesbloquearUnMar(String aliasJugador, Double monedas);

    Mar obtenerElEstadoDeUnMarPorNombre(String nombre);

    boolean obtenerElEstadoDelMarSegunELJugador(Mar mar, Jugador jugadorActual);

    Double descontarLasMonedasDelJugador(Mar mar, Jugador jugador);

    Boolean cambiarElEstadoDelMarADesbloqueado(Mar mar, Jugador jugador);

    List<Mar> obtenerTodaListaDeMares();

    Mar obtenerUnMarPorNombre(String nombreMar);

    Mar obtenerMarPorId(Long idMar);

    Boolean desbloquearMarSegunElJugador(Mar mar, Jugador jugador);
}
