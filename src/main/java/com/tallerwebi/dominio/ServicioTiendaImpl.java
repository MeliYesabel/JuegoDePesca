package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.Objeto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServicioTiendaImpl implements ServicioTienda {

    private List<Objeto> listaObjetos;

    public ServicioTiendaImpl() {
        listaObjetos = new ArrayList<Objeto>();
    }


    @Override
    public Boolean comprarObjeto(Jugador jugador, Integer idObjeto) {

        if (jugador == null || idObjeto == null) {
            throw new ParametroInvalidoException("El jugador o el id del objeto no pueden ser nulos");

        }

        Objeto objeto = buscarObjeto(idObjeto);

        if (objeto == null) {
            throw new ObjetoInexistenteException("El objeto no existe");
        }

        if(jugador.getMonedas() < objeto.getPrecioObjeto()){
            throw new MonedasInsuficientesException("monedas insuficientes");
        }

                    jugador.setMonedas(jugador.getMonedas() - objeto.getPrecioObjeto());

                    return Boolean.TRUE;

    }

    public Objeto buscarObjeto(Integer idObjeto) {
        for (Objeto objeto : listaObjetos) {
            if (objeto != null && objeto.getIdObjeto().equals(idObjeto)) {
                return objeto;
            }
        }

           return null;
    }

    public void agregarObjetoDisponible(Objeto objeto) {
        this.listaObjetos.add(objeto);
    }

    public List<Objeto> getListaObjetos() {
        return listaObjetos;
    }
}