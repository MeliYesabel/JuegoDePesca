package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ObjetoInexistenteException;
import com.tallerwebi.dominio.excepcion.ParametroInvalidoException;
import com.tallerwebi.infraestructura.RepositorioPez;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicioTurnoImpl implements ServicioTurno {

    private RepositorioPez repositorioPez;

    public ServicioTurnoImpl(RepositorioPez repositorioPez) {
        this.repositorioPez = repositorioPez;
    }
    @Override
    public Turno crearUnTurno(Run run) {

        if (run.getCebo() <= 0){
            throw new ParametroInvalidoException("No hay cebo para crear un turno.");
        }

       return new Turno();
    }

    @Override
    public List<Pez> tomarTresPecesParaElTurno(Run run) {
        List<Pez> todosLosPecesDelMar = run.getMar().getPeces();
        Collections.shuffle(todosLosPecesDelMar); // se mesclan
        return todosLosPecesDelMar.subList(0, 3);
    }

    @Override
    public List<Pez> guardarLosTresPecesParaElTurno(Run run) {
        return List.of();
    }

    @Override
    public Pez seleccionarUnPez(List<Pez> pecesGenerados, Integer posicionDelPez) {

        if (posicionDelPez <= 3) {
            return pecesGenerados.get(posicionDelPez);
        }
        throw new ObjetoInexistenteException("No existe");
    }

    @Override
    public void restarCeboEquipado() {

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
    public List<Pez> obtenerTresPecesDelMar(Long idMar) {
        List<Pez> todosLosPecesDelMar = repositorioPez.buscarPorIdMar(idMar);
        Collections.shuffle(todosLosPecesDelMar);
        return todosLosPecesDelMar.stream().limit(3).collect(Collectors.toList());
    }

}
