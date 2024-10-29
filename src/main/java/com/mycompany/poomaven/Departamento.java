package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Departamento {
    private String nombre;
    private int administrativoId;

    public Departamento(String nombre, int administrativoId) {
        this.nombre = nombre;
        this.administrativoId = administrativoId;
    }

    // Método para guardar en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO departamentos (nombre, administrativo_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, administrativoId);
            pstmt.executeUpdate();
            System.out.println("Departamento " + nombre + " guardado en la base de datos.");
        }
    }
}
