package com.tallerwebi.dominio;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.transaction.Transactional;
import java.util.List;

@Service("serviciosColecciones")
@Transactional
public class ServicioColeccionesImpl implements ServicioColecciones {

    @Override
    public DatosColeccion obtenerDatosColeccion(String nombre, String peso, String rareza, Integer valorBase, Integer cantidadDePeces) {

        DatosColeccion coleccion = new DatosColeccion(nombre,peso,rareza,valorBase, cantidadDePeces);

        return coleccion;
    }
    /*@Override
    Lis<Coleccion> obtenerColeccionesFiltradas(String filtro) {
        // Implementar la lógica para filtrar las colecciones según el filtro proporcionado
        return null; // Retornar una lista filtrada de colecciones
     */
    /*@Override
    List<Coleccion> obtenerColeccionesOrdenadas(String criterio) {
        // Implementar la lógica para ordenar las colecciones según el criterio proporcionado
        return null; // Retornar una lista ordenada de colecciones
     */

    }
    /*

    */