package com.tallerwebi.dominio;

import java.util.List;

public class Run {
    private Integer cebo;
    private Mar mar;
    private List<Pez> pecesPescados;
    private List<Turno> historialTurnos;

    public Run(Mar mar) {
        this.mar = mar;
    }

    public Integer getCebo() {
        return cebo;
    }
    public void setCebo(Integer cebo) {
        this.cebo = cebo;
    }
    public Mar getMar() {
        return mar;
    }
    public void setMar(Mar mar) {
        this.mar = mar;
    }
    public void setPecesPescados(List<Pez> pecesPescados) {
        this.pecesPescados = pecesPescados;
    }
    public void setHistorialTurnos(List<Turno> historialTurnos) {
        this.historialTurnos = historialTurnos;
    }

    public Integer getCeboRestante() {
        return cebo;
    }

    public void restarCebo() {
        if (cebo == 0) {
            throw new IllegalStateException("Se acabo la pesca, no hay mas cebos disponibles.");
        } else {
            cebo--;
        }
    }
}
