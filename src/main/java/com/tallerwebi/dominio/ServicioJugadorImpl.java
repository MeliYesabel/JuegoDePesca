package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ObjetoInexistenteException;
import com.tallerwebi.dominio.excepcion.ParametroInvalidoException;
import com.tallerwebi.infraestructura.RepositorioJugadorImpl;
import com.tallerwebi.infraestructura.RepositorioObjetoImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioJugadorImpl implements ServicioJugador {

    RepositorioJugador repositorioJugador;
    RepositorioObjeto repositorioObjeto;

    public ServicioJugadorImpl(RepositorioJugador repositorioJugador, RepositorioObjeto repositorioObjeto) {
        this.repositorioJugador = repositorioJugador;
        this.repositorioObjeto = repositorioObjeto;

    }

    @Override
    public Boolean equipaCaniaAPersonaje(Jugador jugador, Integer idObjeto) {

        if(jugador == null || idObjeto == null){
            throw new ParametroInvalidoException("El jugador o el id del objeto no puede ser nulo");
        }
        Objeto objeto = repositorioObjeto.buscarObjeto(idObjeto);
        if(objeto == null){
            throw new ObjetoInexistenteException("Objeto no encontrado");
        }

        jugador.agregarObjeto(objeto);
        repositorioJugador.guardarJugador(jugador); //esta bien poner este repo aca? o seria como agregar un jugador cada vez que equipe una caña?
        return Boolean.TRUE;
    }
}
