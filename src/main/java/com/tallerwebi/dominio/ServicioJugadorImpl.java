package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioJugadorImpl implements ServicioJugador {

    /*repositorios*/
    private RepositorioJugador repositorioJugador;

    /*constructor*/
    public ServicioJugadorImpl(RepositorioJugador repositorioJugador){
        this.repositorioJugador = repositorioJugador;
    }

    /*metodos*/
    @Override
    public Jugador buscarJugadorPorId(Long idJugador) {
        Jugador resultado = repositorioJugador.buscarjugadorPorId(idJugador);
        if (resultado != null){
            return resultado;
        }
        return null;
    }
}
