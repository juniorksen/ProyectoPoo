package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Farmacia {
    private String nombre;
    private String direccion;
    private int id; // Para almacenar el ID después de la inserción

    public Farmacia(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    // Método para guardar en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO farmacia (nombre, direccion) VALUES (?, ?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, direccion);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id"); // Obtener el ID generado
                System.out.println("Farmacia guardada con ID: " + id);
            }
        }
    }

    public int getId() {
        return id;
    }
}
