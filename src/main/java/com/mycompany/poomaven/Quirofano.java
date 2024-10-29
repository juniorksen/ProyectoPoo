package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Quirofano {
    private String nombre;
    private String ubicacion;
    private int id; // Para almacenar el ID después de la inserción

    public Quirofano(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    // Método para guardar en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO quirofanos (nombre, ubicacion) VALUES (?, ?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, ubicacion);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id"); // Obtener el ID generado
                System.out.println("Quirófano guardado con ID: " + id);
            }
        }
    }

    public int getId() {
        return id;
    }
}
