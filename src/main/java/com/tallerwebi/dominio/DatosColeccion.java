package com.tallerwebi.dominio;

public class DatosColeccion {
    private String nombre;
    private String peso;
    private String rareza;
    private Integer valorBase;
    private Integer cantidadDePeces ;
    /// constructor///
    public DatosColeccion(String nombre,
                          String peso,
                          String rareza,
                          Integer valorBase,
                          Integer cantidadDePeces) {

        this.nombre = nombre;
        this.peso = peso;
        this.rareza = rareza;
        this.valorBase = valorBase;
        this.cantidadDePeces = cantidadDePeces;
    }
// constructor por defecto///
    public DatosColeccion() {

    }
/// metodos de acceso///
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }


    public String getRareza() {
        return rareza;
    }

    public void setRareza(String rareza) {
        this.rareza = rareza;
    }
    public Integer getValorBase() {
        return valorBase;
    }
    public void setValorBase(Integer valorBase) {
        this.valorBase = valorBase;
    }
    public Integer getCantidadDePeces() {
        return cantidadDePeces;
    }
    public void setCantidadDePeces(Integer cantidadDePeces) {
        this.cantidadDePeces = cantidadDePeces;
    }

}
