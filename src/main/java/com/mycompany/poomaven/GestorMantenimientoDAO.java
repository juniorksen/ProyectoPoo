package com.mycompany.poomaven;

import java.util.List;

public interface GestorMantenimientoDAO {
    List<GestorMantenimientoPlanta> obtenerTodosLosGestores();
    List<GestorMantenimientoPlanta> buscarGestoresPorProyectosActivos(int proyectosActivos);
    GestorMantenimientoPlanta obtenerGestorPorNombre(String nombre);
}
