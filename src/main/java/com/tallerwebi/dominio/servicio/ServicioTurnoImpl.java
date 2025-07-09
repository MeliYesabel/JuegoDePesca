package com.tallerwebi.dominio.servicio;

import com.tallerwebi.dominio.entidad.Turno;
import com.tallerwebi.dominio.entidad.Pez;
import com.tallerwebi.dominio.entidad.Run;
import com.tallerwebi.dominio.excepcion.ObjetoInexistenteException;
import com.tallerwebi.dominio.excepcion.ParametroInvalidoException;
import com.tallerwebi.dominio.repositorio.RepositorioPez;
import com.tallerwebi.dominio.repositorio.RepositorioTurno;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicioTurnoImpl implements ServicioTurno {

    private final RepositorioTurno repositorioTurno;
    private RepositorioPez repositorioPez;

    public ServicioTurnoImpl(RepositorioPez repositorioPez, RepositorioTurno repositorioTurno) {
        this.repositorioPez = repositorioPez;
        this.repositorioTurno = repositorioTurno;
    }
    @Override
    public Turno crearUnTurno(Run run) {

        if (run.getCebo() <= 0){
            throw new ParametroInvalidoException("No hay cebo para crear un turno.");
        }

       return new Turno();
    }

    @Override
    public Turno crearUnTurno() {
        Turno turno = new Turno();
        repositorioTurno.guardarTurno(turno);
        return turno;
    }

    @Override
    public List<Pez> tomarTresPecesParaElTurno(Run run) {
        List<Pez> todosLosPecesDelMar = run.getMar().getPeces();
        Collections.shuffle(todosLosPecesDelMar); // se mesclan
        return todosLosPecesDelMar.subList(0, 3);
    }

    @Override
    public Pez seleccionarUnPez(List<Pez> pecesGenerados, Integer posicionDelPez) {

        if (posicionDelPez <= 3) {
            return pecesGenerados.get(posicionDelPez);
        }
        throw new ObjetoInexistenteException("No existe");
    }

    @Override
public List<Pez> obtenerTresPecesAleatorios() {
        List<Pez> todosLosPeces = repositorioPez.obtenerTodosLosPeces();
        Collections.shuffle(todosLosPeces);
        return todosLosPeces.stream().limit(3).collect(Collectors.toList());
    }
@Override

public Pez pescarPezPorId(Long id) {
        return repositorioPez.obtenerTodosLosPeces()
                .stream()
                .filter(pez -> pez.getId_pez().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Boolean pesca(Pez pezSeleccionado) {
        double probabilidad = pezSeleccionado.getRareza().getProbabilidadAtrapar();
        double aleatorio = Math.random();

        return aleatorio < probabilidad;
    }


}
