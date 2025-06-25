package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioRunImpl implements ServicioRun {

    ServicioTurno servicioTurno;

    public ServicioRunImpl(ServicioTurno servicioTurno) {
        this.servicioTurno = servicioTurno;

    }
    @Override
    public void jugarTurno(HttpSession httpSession) {
    }

    @Override
    public Boolean hayCeboJugador(Run run) {
        return run != null
                && run.getJugador() != null
                && run.getJugador().getCeboEquipado() != null
                && run.getJugador().getCeboEquipado() > 0;
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
