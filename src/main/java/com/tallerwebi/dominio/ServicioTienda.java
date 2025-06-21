package com.tallerwebi.dominio;


import java.util.List;

public interface ServicioTienda {
    void agregarYGuardarObjeto(Objeto objeto);
    Boolean comprarObjeto(Jugador jugador, Long idObjeto);
    void agregarObjetoDisponible(Objeto objeto);
    List<Objeto> getListaObjetos();
    void inicializarTienda();
}
