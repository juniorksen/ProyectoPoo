package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GestorMantenimientoDAOImpl implements GestorMantenimientoDAO {
    private Connection connection;

    public GestorMantenimientoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    // Método para obtener todos los gestores de mantenimiento
    @Override
    public List<GestorMantenimientoPlanta> obtenerTodosLosGestores() {
        List<GestorMantenimientoPlanta> gestores = new ArrayList<>();
        try {
            // Verifica si la conexión sigue abierta
            if (connection == null || connection.isClosed()) {
                throw new IllegalStateException("Conexión a la base de datos cerrada");
            }

            String query = "SELECT * FROM gestion_mantenimiento";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Crear un objeto GestorMantenimientoPlanta desde la base de datos
                GestorMantenimientoPlanta gestor = new GestorMantenimientoPlanta(
                        resultSet.getString("nombre"),
                        resultSet.getString("areaResponsabilidad"),
                        resultSet.getString("tipoDeEquipo"),
                        resultSet.getInt("proyectosActivos"),
                        resultSet.getString("nivelEducativo"),
                        resultSet.getString("especialidadDocente"),
                        connection // Mantener conexión si es necesario
                );
                
                // Obtener actividad y detalle
                String actividad = resultSet.getString("actividad");
                String detalle = resultSet.getString("detalle");
                
                // Establecer actividad y detalle en el objeto gestor
                gestor.setActividad(actividad);
                gestor.setDetalle(detalle);

                gestores.add(gestor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gestores;
    }

    // Implementación del método para filtrar por proyectos activos
    @Override
    public List<GestorMantenimientoPlanta> buscarGestoresPorProyectosActivos(int proyectosActivos) {
        List<GestorMantenimientoPlanta> gestores = new ArrayList<>();
        try {
            if (connection == null || connection.isClosed()) {
                throw new IllegalStateException("Conexión a la base de datos cerrada");
            }

            String query = "SELECT * FROM gestion_mantenimiento WHERE proyectosActivos = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, proyectosActivos);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                GestorMantenimientoPlanta gestor = new GestorMantenimientoPlanta(
                        resultSet.getString("nombre"),
                        resultSet.getString("areaResponsabilidad"),
                        resultSet.getString("tipoDeEquipo"),
                        resultSet.getInt("proyectosActivos"),
                        resultSet.getString("nivelEducativo"),
                        resultSet.getString("especialidadDocente"),
                        connection
                );

                // Obtener actividad y detalle
                String actividad = resultSet.getString("actividad");
                String detalle = resultSet.getString("detalle");
                
                // Establecer actividad y detalle en el objeto gestor
                gestor.setActividad(actividad);
                gestor.setDetalle(detalle);
                
                gestores.add(gestor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gestores;
    }

    // Método para obtener un gestor por nombre
    @Override
    public GestorMantenimientoPlanta obtenerGestorPorNombre(String nombre) {
        GestorMantenimientoPlanta gestor = null;
        String query = "SELECT * FROM gestion_mantenimiento WHERE nombre = ?";

        try {
            if (connection == null || connection.isClosed()) {
                throw new IllegalStateException("Conexión a la base de datos cerrada");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nombre);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                gestor = new GestorMantenimientoPlanta(
                        resultSet.getString("nombre"),
                        resultSet.getString("areaResponsabilidad"),
                        resultSet.getString("tipoDeEquipo"),
                        resultSet.getInt("proyectosActivos"),
                        resultSet.getString("nivelEducativo"),
                        resultSet.getString("especialidadDocente"),
                        connection
                );

                // Obtener actividad y detalle
                String actividad = resultSet.getString("actividad");
                String detalle = resultSet.getString("detalle");
                
                // Establecer actividad y detalle en el objeto gestor
                gestor.setActividad(actividad);
                gestor.setDetalle(detalle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gestor;
    }
}
