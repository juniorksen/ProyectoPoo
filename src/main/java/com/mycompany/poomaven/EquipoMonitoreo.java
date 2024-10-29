package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EquipoMonitoreo {
    private String nombre;
    private String tipo; // Tipo de equipo (ej. Monitor cardíaco, oxímetro)

    public EquipoMonitoreo(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    // Método para guardar el equipo en la base de datos
    public void guardarEnBaseDatos(Connection connection, int areaObservacionId) throws SQLException {
        String insertQuery = "INSERT INTO equipo_monitoreo (nombre, tipo, area_observacion_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, tipo);
            pstmt.setInt(3, areaObservacionId);
            pstmt.executeUpdate();
            System.out.println("Equipo de monitoreo '" + nombre + "' guardado en la base de datos.");
        }
    }
}
