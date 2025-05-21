package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ServicioRunImpl implements ServicioRun {
    private Number cantidadDeCebo;
    private Mar mar;
    private List<Turno> turnos;


    public ServicioRunImpl(Mar mar) {
        this.mar = mar;
    }
    @Override
    public void jugarTurno(Run run, ServicioTurnoImpl turno) {
        turno.atrapar(turno.devolverPezSeleccionado(turno.generarPeces(mar),turno.obtenerNumeroDePezSeleccionado()));
        run.restarCebo();

    }

    public Mar getMar() {
        return mar;
    }

    public void setMar(Mar mar) {
        this.mar = mar;
    }
}
