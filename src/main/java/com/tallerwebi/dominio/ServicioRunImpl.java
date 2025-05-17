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
    //TODAVIA NO SE COMO OBTENIDAMOS EL NUMERO DEL PEZ SELECCIONADO xd
    public Integer obtenerNumeroDePezSeleccionado(){
        return 1;
    };

    @Override
    public List<Pez> generarPeces() {
        List<Pez> disponibles = new ArrayList<>(mar.getPeces());

        // condicion de que haya mas de 3 pe
        if (disponibles.size() < 3) {
            throw new IllegalStateException("No hay cebo restante, se acabo la pesca.");
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
    public Pez devolverPezSeleccionado(List<Pez> pecesGenerados, Integer posicionDelPez) {
        return pecesGenerados.get(posicionDelPez);
    }

    @Override
    public Boolean atrapar(Pez pez){
        return Math.random() < pez.getRareza().getProbabilidadAtrapado(); // lo atrapara dependiedno la probailidad
    }
    @Override
    public void jugarTurno(Run run) {
        atrapar(devolverPezSeleccionado( generarPeces(),obtenerNumeroDePezSeleccionado()));
        run.restarCebo();

    }
}
