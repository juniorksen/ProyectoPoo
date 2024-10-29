package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProgramaRehabilitacion {
    private String nombre;
    private String descripcion;

    public ProgramaRehabilitacion(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Método para guardar el programa de rehabilitación en la base de datos
    public void guardarEnBaseDatos(Connection connection, int salaId) throws SQLException {
        String insertQuery = "INSERT INTO programa_rehabilitacion (nombre, descripcion, sala_rehabilitacion_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, descripcion);
            pstmt.setInt(3, salaId);
            pstmt.executeUpdate();
            System.out.println("Programa de Rehabilitación guardado en la base de datos.");
        }
    }
}
