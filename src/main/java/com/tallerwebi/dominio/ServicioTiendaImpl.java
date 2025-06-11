package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.RepositorioObjeto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServicioTiendaImpl implements ServicioTienda {

    private List<Objeto> listaObjetos;
    private RepositorioObjeto repositorioObjeto;

    public ServicioTiendaImpl( RepositorioObjeto repositorioObjeto ) {
        this.repositorioObjeto = repositorioObjeto;
        listaObjetos = new ArrayList<Objeto>();
    }


    @Override
    public Boolean comprarObjeto(Jugador jugador, Integer idObjeto) {

        if (jugador == null || idObjeto == null) {
            throw new ParametroInvalidoException("El jugador o el id del objeto no pueden ser nulos");

        }

        Objeto objeto = repositorioObjeto.buscarObjeto(idObjeto);

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

    public void agregarYGuardarObjeto(Objeto objeto) {
        // Guardás el objeto en la base de datos
        repositorioObjeto.guardarObjeto(objeto);

        // Lo agregás a la lista interna para tenerlo disponible en el servicio
        this.listaObjetos.add(objeto);
    }

    public List<Objeto> getListaObjetos() {
        //agregar objetos si esta vacio y sacarla del controlador tienda
        return listaObjetos;
    }
}