package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Objeto;

import java.util.List;

public interface RepositorioObjeto /*extends JpaRepository<Objeto,Integer>*/ {
    Objeto buscarObjeto(int id);
    void guardarObjeto(Objeto objeto);
    List<Objeto> buscarListaObjetosPorNombreLike();

}
