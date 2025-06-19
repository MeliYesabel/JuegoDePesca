package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.MonedasInsuficientesException;
import com.tallerwebi.dominio.excepcion.ObjetoInexistenteException;
import com.tallerwebi.dominio.excepcion.ObjetoYaCompradoException;
import com.tallerwebi.dominio.excepcion.ParametroInvalidoException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServicioTiendaImpl implements ServicioTienda {

    private List<Objeto> listaObjetos;
    private RepositorioObjeto repositorioObjeto;
    private RepositorioJugador repositorioJugador;

    public ServicioTiendaImpl( RepositorioObjeto repositorioObjeto, RepositorioJugador repositorioJugador) {
        this.repositorioObjeto = repositorioObjeto;
        this.repositorioJugador = repositorioJugador;
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

        if(jugador.getObjetosComprados().contains(objeto)){
            throw new ObjetoYaCompradoException("El jugador ya esta tiene este objeto");
        }

        if(jugador.getMonedas() < objeto.getPrecioObjeto()){
            throw new MonedasInsuficientesException("monedas insuficientes");
        }

                    jugador.setMonedas(jugador.getMonedas() - objeto.getPrecioObjeto());

                    objeto.setJugador(jugador);
                    jugador.agregarObjeto(objeto);
                    repositorioJugador.guardarJugador(jugador);


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

    public void inicializarTienda() {
        if (this.getListaObjetos().isEmpty()) {
           /* Objeto objeto1 = new Objeto(100.0, "caña");
            Objeto objeto2 = new Objeto(150.0, "caña");
            this.agregarYGuardarObjeto(objeto1);
            this.agregarYGuardarObjeto(objeto2);*/
          this.listaObjetos =  repositorioObjeto.obtenerTodosLosObjetos();
        }
    }


    public List<Objeto> getListaObjetos() {
        //agregar objetos si esta vacio y sacarla del controlador tienda
        return listaObjetos;
    }
}