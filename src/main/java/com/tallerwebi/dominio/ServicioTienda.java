package com.tallerwebi.dominio;


import com.tallerwebi.dominio.excepcion.Objeto;

import java.util.List;

public interface ServicioTienda {

    Boolean comprarObjeto(Jugador jugador, Integer idObjeto);
    void agregarObjetoDisponible(Objeto objeto);
    List<Objeto> getListaObjetos();
}
