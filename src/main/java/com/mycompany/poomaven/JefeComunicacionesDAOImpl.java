package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JefeComunicacionesDAOImpl implements JefeComunicacionesDAO {
    private Connection connection;

    public JefeComunicacionesDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<JefeComunicaciones> obtenerTodosLosJefesComunicaciones() {
        List<JefeComunicaciones> jefesComunicaciones = new ArrayList<>();

        try {
            String query = "SELECT nombre, canalDeComunicacion, tipoDeMensaje, proyectosActivos, nivelEducativo, especialidad FROM jefe_comunicaciones";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String canalDeComunicacion = resultSet.getString("canalDeComunicacion");
                String tipoDeMensaje = resultSet.getString("tipoDeMensaje");
                int proyectosActivos = resultSet.getInt("proyectosActivos");
                String nivelEducativo = resultSet.getString("nivelEducativo");
                String especialidad = resultSet.getString("especialidad");

                // Crear un nuevo objeto JefeComunicaciones con los datos obtenidos
                JefeComunicaciones jefe = new JefeComunicaciones(
                        nombre,
                        canalDeComunicacion,
                        tipoDeMensaje,
                        proyectosActivos,
                        nivelEducativo,
                        especialidad,
                        connection
                );

                jefesComunicaciones.add(jefe);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jefesComunicaciones;
    }
}
