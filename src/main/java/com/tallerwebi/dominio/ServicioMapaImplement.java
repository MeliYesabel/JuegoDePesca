package com.tallerwebi.dominio;


import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service /*decir que es un clase servicio*/
@Transactional /*que sea mas raapido ? */

public class ServicioMapaImplement implements ServicioMapa {

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


}
