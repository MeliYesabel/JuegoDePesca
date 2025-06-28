package com.tallerwebi.dominio.servicio;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Objeto;
import com.tallerwebi.dominio.excepcion.ObjetoInexistenteException;
import com.tallerwebi.dominio.excepcion.ParametroInvalidoException;
import com.tallerwebi.dominio.repositorio.RepositorioJugador;
import com.tallerwebi.dominio.repositorio.RepositorioObjeto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioJugadorImpl implements ServicioJugador {

    RepositorioJugador repositorioJugador;
    RepositorioObjeto repositorioObjeto;

    public ServicioJugadorImpl(RepositorioJugador repositorioJugador,RepositorioObjeto repositorioObjeto) {
        this.repositorioJugador = repositorioJugador;
        this.repositorioObjeto = repositorioObjeto;

    }
    @Override
    public Jugador buscarJugadorPorId(Long idJugador) {
        return  repositorioJugador.buscarjugadorPorId(idJugador);
    }

    @Override
    public Boolean equipaCaniaAPersonaje(Jugador jugador, Long idObjeto) {

        if(jugador == null || idObjeto == null){
            throw new ParametroInvalidoException("El jugador o el id del objeto no puede ser nulo");
        }

        Jugador jugadorGestionado = repositorioJugador.buscarJugador(jugador.getId());
        if(jugadorGestionado == null){
            throw new ParametroInvalidoException("Jugador no existe");
        }

        Objeto objeto = repositorioObjeto.buscarObjeto(idObjeto);
        if(objeto == null){
            throw new ObjetoInexistenteException("Objeto no encontrado");
        }

        if(!jugadorGestionado.getObjetosComprados().contains(objeto)){
            throw new ObjetoInexistenteException("Objeto no encontrado en el inventario de este jugador");
        }



      //  jugador.agregarObjeto(objeto);
        jugadorGestionado.setCaniaActiva(objeto);
        repositorioJugador.guardarJugador(jugadorGestionado); //esta bien poner este repo aca? o seria como agregar un jugador cada vez que equipe una caña?

        return Boolean.TRUE;
    }

    @Override
    public Jugador inicializarJugador() {
        Jugador jugador = new Jugador();
        repositorioJugador.guardarJugador(jugador);
        return jugador;

    }
}
