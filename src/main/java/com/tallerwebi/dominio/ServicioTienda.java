package com.tallerwebi.dominio;


import java.util.List;

public interface ServicioTienda {
    void agregarYGuardarObjeto(Objeto objeto);
    Boolean comprarObjeto(Jugador jugador, Integer idObjeto);
    void agregarObjetoDisponible(Objeto objeto);
    List<Objeto> getListaObjetos();
}
