package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Clase base Tratamiento
abstract class Tratamiento {
    protected String nombre;
    protected int duracionDias; // Duración del tratamiento en días

    public Tratamiento(String nombre, int duracionDias) {
        this.nombre = nombre;
        this.duracionDias = duracionDias;
    }

    public abstract void aplicarTratamiento(); // Método abstracto

    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO tratamientos (nombre, duracion_dias) VALUES (?, ?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, duracionDias);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Tratamiento " + nombre + " guardado con ID: " + rs.getInt("id"));
            }
        }
    }
}
