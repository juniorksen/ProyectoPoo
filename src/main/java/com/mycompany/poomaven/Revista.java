package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Revista {
    private String titulo;
    private String fechaPublicacion;

    public Revista(String titulo, String fechaPublicacion) {
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
    }

    // Método para guardar la revista en la base de datos
    public void guardarEnBaseDatos(Connection connection, int salaRecepcionId) throws SQLException {
        String insertQuery = "INSERT INTO revista (titulo, fecha_publicacion, sala_recepcion_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, fechaPublicacion);
            pstmt.setInt(3, salaRecepcionId);
            pstmt.executeUpdate();
            System.out.println("Revista '" + titulo + "' guardada en la base de datos.");
        }
    }
}
