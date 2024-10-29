package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Suministro {
    private String nombre;
    private double cantidad;
    private String unidadMedida;

    public Suministro(String nombre, double cantidad, String unidadMedida) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
    }

    // Método para guardar el suministro en la base de datos
    public void guardarEnBaseDatos(Connection connection, int inventarioId) throws SQLException {
        String insertQuery = "INSERT INTO suministro (nombre, cantidad, unidad_medida, inventario_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            pstmt.setDouble(2, cantidad);
            pstmt.setString(3, unidadMedida);
            pstmt.setInt(4, inventarioId);
            pstmt.executeUpdate();
            System.out.println("Suministro " + nombre + " guardado en la base de datos.");
        }
    }
}
