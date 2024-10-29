package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Medicamento {
    private String nombre;
    private double precio;
    private int farmaciaId;

    public Medicamento(String nombre, double precio, int farmaciaId) {
        this.nombre = nombre;
        this.precio = precio;
        this.farmaciaId = farmaciaId;
    }

    // Método para guardar en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO medicamentos (nombre, precio, farmacia_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            pstmt.setDouble(2, precio);
            pstmt.setInt(3, farmaciaId);
            pstmt.executeUpdate();
            System.out.println("Medicamento " + nombre + " guardado en la base de datos.");
        }
    }
}