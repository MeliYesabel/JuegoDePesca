package com.tallerwebi.dominio.servicio;

import com.tallerwebi.dominio.entidad.Pez;
import com.tallerwebi.dominio.entidad.Run;
import com.tallerwebi.dominio.repositorio.RepositorioRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioRunImpl implements ServicioRun {

    ServicioTurno servicioTurno;

    @Autowired
    private RepositorioRun repositorioRun;

    @Autowired
    public ServicioRunImpl(RepositorioRun repositorioRun) {
        this.repositorioRun = repositorioRun;
    }
    @Override
    public void jugarTurno(HttpSession httpSession) {
    }

    @Override
    public Boolean hayCeboJugador(Run run) {
        return run != null
                && run.getCebo() > 0;
    }
        @Override
    public List<Pez> obtenerPecesPescados(Run run) {
        return List.of();
    }

    @Override
    public Double calcularGanancia(Run run) {
        return 0.0;
    }

    @Override
    public Integer getCantidadTurnosJugados(Run run) {
        return 0;
    }

    @Override
    public void guardarRun(Run run) {
        repositorioRun.crearUnaRun(run);
    }

    @Override
    public Run obtenerRun(Long idRun) {
        return repositorioRun.obtenerRunPorId(idRun);
    }

    @Override
    public void agregarPezAListaPecesPescados(Pez pezSeleccionado, Long idRun) {
        repositorioRun.obtenerRunPorId(idRun).agregarPecesPescados(pezSeleccionado);
    }

}
