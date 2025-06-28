package com.tallerwebi.dominio.repositorio;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Mar;

import java.util.List;

public interface RepositorioMar  {

    List<Mar> obtenerLaListaCompletaDeTodosLosMares();

    List<Mar> obtenerListaDeMaresDesbloqueados();

    List<Mar> obtenerListaDeMaresBloqueados();

    Mar obtenerMarPorNombre(String nombreMar);

    Mar obtenerMarPorNombreSiEsteEstaDesbloqeuado(String nombre);

    Mar obtenerElPrecioDeUnMarBloqueado(String marBloqueado);

    boolean obtenerElEstadoMarDelJugador(Mar mar, Jugador jugadorActual);

    Mar obtenerMarPorId(Long idMar);
}
