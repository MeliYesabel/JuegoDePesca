package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioMar  {

    List<Mar> obtenerLaListaCompletaDeTodosLosMares();

    List<Mar> obtenerListaDeMaresDesbloqueados();

    List<Mar> obtenerListaDeMaresBloqueados();

    Mar obtenerMarPorNombre(String nombreMar);

    Mar obtenerMarPorNombreSiEsteEstaDesbloqeuado(String nombre);

    Mar obtenerElPrecioDeUnMarBloqueado(String marBloqueado);

    boolean obtenerElEstadoMarDelJugador(Mar mar, Jugador jugadorActual);
}
