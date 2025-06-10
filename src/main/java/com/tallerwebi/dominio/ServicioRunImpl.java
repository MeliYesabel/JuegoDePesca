package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioRunImpl implements ServicioRun {
    @Override
    public boolean hayCebo(Integer cebos) {
        return cebos != null && cebos > 0;
    }

    @Override
    public String jugarTurno(ModelMap model) {
        return "";
    }
}
