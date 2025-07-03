package com.tallerwebi.dominio.entidad;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("JUGADOR")
public class Jugador extends UsuarioAuth {

   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;*/

    private static final Double MONEDAS_INICIALES = 200.0;

    private String alias;
    private double monedas;
    private String nombre;
    private Integer cant_carnada;
    private Integer ceboEquipado;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "jugador_id")
    private List<Objeto> objetosComprados = new ArrayList<>();

   @ManyToOne
   private Objeto caniaActiva;

   @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<JugadorMar> mares = new ArrayList<>();


   public Jugador(String nombre){
        this.monedas = 0.0;
    //objetosComprados = new ArrayList<>(); preuguntar porque fallaba si la inicializo en el constructor
        this.nombre = nombre;
        caniaActiva = null;

    }
    public Jugador(String nombre, String alias, double monedas, Integer cant_carnada){
        this.nombre = nombre;
        this.alias = alias;
        this.monedas = monedas;
        this.cant_carnada = cant_carnada;
    }

    public Jugador() {
       this.monedas  = MONEDAS_INICIALES;
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

    public void  setMonedas(double monedas) {
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

    public void agregarObjeto(Objeto objeto){
    objetosComprados.add(objeto); //esto no tendria que estar aca
   }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getCant_carnada() {
        return cant_carnada;
    }

    public void setCant_carnada(Integer cant_carnada) {
        this.cant_carnada = cant_carnada;
    }

    public Integer getCeboEquipado() {
        return ceboEquipado;
    }

    public void setCeboEquipado(Integer ceboEquipado) {
        this.ceboEquipado = ceboEquipado;
    }

    public void sumarGanancia(Integer ganancia) {
    this.monedas += ganancia;
    }
}



