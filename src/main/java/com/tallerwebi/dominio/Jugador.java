package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.EstadisticasJugador;
import com.tallerwebi.dominio.entidad.Logro;
import com.tallerwebi.dominio.entidad.Pescador;
import com.tallerwebi.dominio.entidad.UsuarioAuth;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("JUGADOR")
public class Jugador extends UsuarioAuth {

    private static final Double MONEDAS_INICIALES = 200.0;
    private double monedas;
    private String nombre;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Pez> coleccionDePeces;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Logro> Logros;

    @ManyToOne(fetch = FetchType.EAGER) // solo un pescador seleccionado
    private Pescador pescadorSeleccionado;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Mar> maresDesbloqueados;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private EstadisticasJugador estadisticas;

    //---------------------------------------------------------
    @OneToMany
    private List<Objeto> objetosComprados = new ArrayList<>();

    @ManyToOne
    private Objeto caniaActiva;


    /*
    public Jugador(String nombre){
        this.monedas = 0.0;
        //objetosComprados = new ArrayList<>(); preuguntar porque fallaba si la inicializo en el constructor
        this.nombre = nombre;
        caniaActiva = null;

    }
    */
    public Jugador() {
        this.monedas = MONEDAS_INICIALES;
    }

    public Objeto getCaniaActiva() {
        return caniaActiva;
    }

    public void setCaniaActiva(Objeto caniaActiva) {
        this.caniaActiva = caniaActiva;
    }

    public double getMonedas() {
        return monedas;
    }

    public void setMonedas(double monedas) {
        this.monedas = monedas;
    }


    public String getNombre() {
        return nombre;
    }

    public List<Objeto> getObjetosComprados() {
        return objetosComprados;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setObjetosComprados(List<Objeto> objetosComprados) {
        this.objetosComprados = objetosComprados;
    }

    public void agregarObjeto(Objeto objeto) {
        objetosComprados.add(objeto); //esto no tendria que estar aca
    }


}



