package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioObjeto /*extends JpaRepository<Objeto,Integer>*/ {
    Objeto buscarObjeto(int id);
    void guardarObjeto(Objeto objeto);
    List<Objeto> buscarListaObjetosPorNombreLike(String nombre);

}
