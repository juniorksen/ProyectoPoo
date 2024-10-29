package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Clase base Examen
abstract class Examen {
    protected String nombre;
    protected double precio;

    public Examen(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public abstract void realizarExamen(); // Método abstracto

    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO examenes (nombre, precio) VALUES (?, ?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            pstmt.setDouble(2, precio);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Examen " + nombre + " guardado con ID: " + rs.getInt("id"));
            }
        }
    }
}
