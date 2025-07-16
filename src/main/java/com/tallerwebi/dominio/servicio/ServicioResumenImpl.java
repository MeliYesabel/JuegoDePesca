package com.tallerwebi.dominio.servicio;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Resumen;
import com.tallerwebi.dominio.entidad.Run;
import com.tallerwebi.dominio.excepcion.NoSePuedodesbloquearElMarException;
import com.tallerwebi.dominio.repositorio.RepositorioJugador;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioResumenImpl implements ServicioResumen {

    RepositorioJugador repositorioJugador;

    public ServicioResumenImpl(RepositorioJugador repositorioJugador) {
        this.repositorioJugador = repositorioJugador;
    }


    @Override
    public void resumen(Resumen resumen, Run run) {

        Jugador jugador = run.getJugador();

        resumen.setPecesPescados(run.getPecesPescados());
        jugador.sumarGanancia(resumen.getTotalGanado());

        repositorioJugador.actualizarDatosDeJugadorYaExistente(jugador);
        }


}
