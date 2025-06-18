package com.tallerwebi.dominio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
        Mar mar = repositorioMar.obtenerMarPorNombreSiEsteEstaDesbloqeuado(nombre);
        if (mar != null) {
            return mar;
        }
        return null;
    }

}
