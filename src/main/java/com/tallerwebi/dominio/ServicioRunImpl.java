package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ServicioRunImpl implements ServicioRun {

    private Mar mar;

    public ServicioRunImpl(Mar mar) {
        this.mar = mar;
    }

    @Override
    public List<Pez> generarPeces() {
        List<Pez> disponibles = new ArrayList<>(mar.getPeces());


        // Asegurarse de que haya al menos 3 peces
        if (disponibles.size() < 3) {
            throw new IllegalStateException("No hay suficientes peces disponibles para generar el turno");
        }

        // Mezclar la lista para aleatoriedad
        Collections.shuffle(disponibles);

        // ACA PODRIA IR TODA LA LOGICA DE PROBAILIDAD PLANEADA PARA LOS PECES
        //Se supone que aca se agarran lo primeros 3 peses
        List<Pez> seleccionados = disponibles.subList(0, 3);

        // Agregarlos al mar
        mar.setPeces(seleccionados);

        // Devolver la lista
        return seleccionados;
    }
}
