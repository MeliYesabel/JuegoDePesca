package com.tallerwebi.dominio.servicio;


import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.JugadorMar;
import com.tallerwebi.dominio.entidad.Mar;
import com.tallerwebi.dominio.repositorio.RepositorioMar;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service /*decir que es un clase servicio*/
@Transactional /*que sea mas raapido ? */
public class ServicioMapaImplement implements ServicioMapa {


    private RepositorioMar repositorioMar;

    public ServicioMapaImplement(RepositorioMar repositorioMar) {
        this.repositorioMar = repositorioMar;
    }

    @Override
    public Boolean calcularSiSePuedeDesbloquearUnMar(String aliasJugador, Double monedas) {
        /*mas adelante debo instanciar el mar */
        Double marSetteado = 100.0;
        Boolean siSePuede =false;

        if (!aliasJugador.isEmpty() && monedas >= marSetteado) {
            siSePuede = true;
        }
        return siSePuede;
    }

    @Override
    public Mar obtenerElEstadoDeUnMarPorNombre(String nombre) {
        return  repositorioMar.obtenerMarPorNombreSiEsteEstaDesbloqeuado(nombre);
    }

    @Override
    public boolean obtenerElEstadoDelMarSegunELJugador(Mar mar, Jugador jugadorActual) {
        boolean estado = false;
        if (repositorioMar.obtenerElEstadoMarDelJugador(mar,jugadorActual)) {
            estado=true;
        }
        return estado;
    }

    @Override
    public Boolean desbloquearMarSegunElJugador(Mar mar, Jugador jugador) {
        if (jugador.getMonedas() >= mar.getPrecio()){
            JugadorMar jm = repositorioMar.obtenerElJugadorMarBuscado(mar,jugador);
            jm.setEstadoBloqueado(false);
            return true;
        }
        return false;
    }

    @Override
    public List<Mar> obtenerTodaListaDeMares() {
        List<Mar>listaMar = repositorioMar.obtenerLaListaCompletaDeTodosLosMares();
        return listaMar;
    }

    @Override
    public Mar obtenerUnMarPorNombre(String nombreMar) {
        return  repositorioMar.obtenerMarPorNombre(nombreMar);

    }

    @Override
    public Mar obtenerMarPorId(Long idMar) {
        Mar mar = repositorioMar.obtenerMarPorId(idMar);
        return mar;
    }


}
