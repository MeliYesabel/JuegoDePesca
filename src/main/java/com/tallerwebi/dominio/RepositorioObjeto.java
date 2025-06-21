package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioObjeto /*extends JpaRepository<Objeto,Integer>*/ {
    Objeto buscarObjeto(Long id);
    void guardarObjeto(Objeto objeto);
    List<Objeto> buscarListaObjetosPorNombreLike(String nombre);
    List<Objeto> obtenerTodosLosObjetos();

}
