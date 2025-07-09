package com.tallerwebi.dominio.servicio;


import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Objeto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public interface ServicioTienda {
    void agregarYGuardarObjeto(Objeto objeto);
    Boolean comprarObjeto(Jugador jugador, Long idObjeto);
    void agregarObjetoDisponible(Objeto objeto);
    List<Objeto> getListaObjetos();
    void inicializarTienda();

    Boolean comprarCarnada(Jugador jugador, Integer cantCarnada);

    public Boolean puedeReclamarMonedas(Jugador jugador);
    public void reclamarMonedas(Long idUsuarioLogueado);


    public Long segundosParaProximoReclamo(Jugador jugador);

}
