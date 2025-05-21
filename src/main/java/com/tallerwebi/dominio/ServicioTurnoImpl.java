package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ServicioTurnoImpl implements ServicioTurno {

    @Override
    public List<Pez> generarPeces(Mar mar) {
        List<Pez> disponibles = new ArrayList<>(mar.getPeces());

        // condicion de que haya mas de 3 pe
        if (disponibles.size() < 3) {
            throw new IllegalStateException("No hay peces suficientes.");
        }
        // shuffle mescla
        Collections.shuffle(disponibles);

        // ACA PODRIA IR TODA LA LOGICA DE PROBAILIDAD PLANEADA PARA LA APARICION DE PECES
        //Se supone que aca se agarran lo primeros 3 peces
        List<Pez> seleccionados = disponibles.subList(0, 3);

        mar.setPeces(seleccionados);
        return seleccionados;
    }

    @Override
    //TODAVIA NO SE COMO OBTENIDAMOS EL NUMERO DEL PEZ SELECCIONADO xd
    public Integer obtenerNumeroDePezSeleccionado(){
        return 1;
    };

    @Override
    public boolean atrapar(Pez pez) {
        return Math.random() < pez.getRareza().getProbabilidadAtrapado();
    }
    @Override
    public Pez devolverPezSeleccionado(List<Pez> pecesGenerados, Integer posicionDelPez) {
        return pecesGenerados.get(posicionDelPez);
    }
}
