package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProtocoloAtencion {
    private String nombre;
    private String descripcion;

    public ProtocoloAtencion(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Método para guardar el protocolo en la base de datos
    public void guardarEnBaseDatos(Connection connection, int centroAtencionId) throws SQLException {
        String insertQuery = "INSERT INTO protocolo_atencion (nombre, descripcion, centro_atencion_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, descripcion);
            pstmt.setInt(3, centroAtencionId);
            pstmt.executeUpdate();
            System.out.println("Protocolo de atención '" + nombre + "' guardado en la base de datos.");
        }
    }
}
