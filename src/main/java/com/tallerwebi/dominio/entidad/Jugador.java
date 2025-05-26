package com.tallerwebi.dominio.entidad;

import java.util.ArrayList;
import java.util.List;

public class Jugador extends UsuarioAuth {
    private Integer monedas;
    private List<Pez> coleccionDePeces;
    private List<Item> inventario;
    private List<Logro> logros;
    private Pescador pescadorSeleccionado;
    private List<Mar> maresDesbloqueados;
    private EstadisticasJugador estadisticas;

    public Jugador() {
        super();
        super.setRol("Jugador");
    }

    public Jugador(Integer id, String username, String mail, String contrasenia, String rol, boolean activo){
        super(id, username, mail, contrasenia, "Jugador", true);
        this.monedas = 0;
        this.coleccionDePeces = new ArrayList<Pez>();
        this.logros = new ArrayList<>();
        this.pescadorSeleccionado = null;
        this.estadisticas = null;
    }

    public Integer getMonedas() {
        return monedas;
    }

    public void setMonedas(Integer monedas) {
        this.monedas = monedas;
    }

    public List<Pez> getColeccionDePeces() {
        return coleccionDePeces;
    }

    public void setColeccionDePeces(List<Pez> coleccionDePeces) {
        this.coleccionDePeces = coleccionDePeces;
    }

    public List<Item> getInventario() {
        return inventario;
    }

    public void setInventario(List<Item> inventario) {
        this.inventario = inventario;
    }

    public List<Logro> getLogros() {
        return logros;
    }

    public void setLogros(List<Logro> logros) {
        this.logros = logros;
    }

    public Pescador getPescadorSeleccionado() {
        return pescadorSeleccionado;
    }

    public void setPescadorSeleccionado(Pescador pescadorSeleccionado) {
        this.pescadorSeleccionado = pescadorSeleccionado;
    }

    public List<Mar> getMaresDesbloqueados() {
        return maresDesbloqueados;
    }

    public void setMaresDesbloqueados(List<Mar> maresDesbloqueados) {
        this.maresDesbloqueados = maresDesbloqueados;
    }

    public EstadisticasJugador getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(EstadisticasJugador estadisticas) {
        this.estadisticas = estadisticas;
    }
}
