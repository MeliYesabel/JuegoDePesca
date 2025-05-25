package com.tallerwebi.dominio;

import org.springframework.ui.ModelMap;

public interface ServicioRun {
    boolean hayCebo(Integer cebos);

    String jugarTurno(ModelMap model);
}
