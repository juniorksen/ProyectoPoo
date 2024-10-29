package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaObservacion {
    private String nombre;
    private List<EquipoMonitoreo> equipos; // Composición: un área de observación contiene múltiples equipos de monitoreo
    private int id; // Para almacenar el ID después de la inserción

    public AreaObservacion(String nombre) {
        this.nombre = nombre;
        this.equipos = new ArrayList<>();
    }

    // Método para agregar un equipo al área de observación
    public void agregarEquipo(EquipoMonitoreo equipo) {
        equipos.add(equipo);
    }

    // Método para guardar el área de observación y sus equipos en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO area_observacion (nombre) VALUES (?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id"); // Obtener el ID generado para el área de observación
                System.out.println("Área de observación guardada con ID: " + id);

                // Guardar los equipos asociados
                for (EquipoMonitoreo equipo : equipos) {
                    equipo.guardarEnBaseDatos(connection, id);
                }
            }
        }
    }

    public int getId() {
        return id;
    }
}
