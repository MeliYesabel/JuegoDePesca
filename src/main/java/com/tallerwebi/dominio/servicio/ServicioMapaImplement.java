package com.tallerwebi.dominio.servicio;


import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.JugadorMar;
import com.tallerwebi.dominio.entidad.Mar;
import com.tallerwebi.dominio.excepcion.NoSePuedodesbloquearElMarException;
import com.tallerwebi.dominio.repositorio.RepositorioJugador;
import com.tallerwebi.dominio.repositorio.RepositorioJugadorMar;
import com.tallerwebi.dominio.repositorio.RepositorioMar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioMapaImplement implements ServicioMapa {


    private RepositorioMar repositorioMar;
    private RepositorioJugador repositorioJugador;
   // private RepositorioJugadorMar repositorioJugadorMar;

    @Autowired
    public ServicioMapaImplement(RepositorioMar repositorioMar, RepositorioJugador repositorioJugador) {
        this.repositorioMar = repositorioMar;
        this.repositorioJugador = repositorioJugador;
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
    public Boolean desbloquearMarSegunElJugador(Mar mar, Jugador jugador) throws NoSePuedodesbloquearElMarException {
        if (jugador.getMonedas() >= mar.getPrecio()){
            cambiarElEstadoDelMarADesbloqueado(mar,jugador);
            descontarLasMonedasDelJugador(mar,jugador);
            //repositorioJugador.guardarJugador(jugador);
            repositorioJugador.actualizarDatosDeJugadorYaExistente(jugador);
            return true;
        }
        throw new NoSePuedodesbloquearElMarException("No tenés monedas suficientes");
    }

    @Override
    public Double descontarLasMonedasDelJugador(Mar mar, Jugador jugador) {
        Double total = jugador.getMonedas() - mar.getPrecio();
        jugador.setMonedas(total);
        return  total;
    }

    @Override
    public Boolean cambiarElEstadoDelMarADesbloqueado(Mar mar, Jugador jugador) {
        JugadorMar jm = repositorioMar.obtenerElJugadorMarBuscado(mar,jugador);
        if (jm.getEstadoBloqueado()){
            jm.setEstadoBloqueado(false);
        //    repositorioJugadorMar.save(jm);
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
