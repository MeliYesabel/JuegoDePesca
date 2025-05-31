package com.tallerwebi.dominio;

import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.util.List;

/**/
@Entity
public class Mar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mar;
    private String nombre;
    private Double precio;
    private String descripcion;
    private Boolean estado = false;

    /*ya que hicimoe @manyToOne en pez esto puede ser opcional*/
    @OneToMany
    private List<Pez> peces;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 /*   public List<Pez> getPeces() {
        return peces;
    }
    public void setPeces(List<Pez> peces) {
        this.peces = peces;
    }
    public void agregarPezAlMar(Pez pez) {
        peces.add(pez);
    }
*/
}
