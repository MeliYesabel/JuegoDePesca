package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioRunImpl implements ServicioRun {
    @Override
    public Boolean hayCebo(Turno turno) {
        if (turno.getCeboRestante() >= 1) {
            return false;
        }
        return true;
    }

    @Override
    public String jugarTurno(ModelMap model) {
        return "";
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

}
