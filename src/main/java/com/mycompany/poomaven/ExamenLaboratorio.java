package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExamenLaboratorio {
    private String nombre;
    private double costo;
    private int laboratorioId;

    public ExamenLaboratorio(String nombre, double costo, int laboratorioId) {
        this.nombre = nombre;
        this.costo = costo;
        this.laboratorioId = laboratorioId;
    }

    // Método para guardar en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO examenes_laboratorio (nombre, costo, laboratorio_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            pstmt.setDouble(2, costo);
            pstmt.setInt(3, laboratorioId);
            pstmt.executeUpdate();
            System.out.println("Examen de laboratorio " + nombre + " guardado en la base de datos.");
        }
    }
}
