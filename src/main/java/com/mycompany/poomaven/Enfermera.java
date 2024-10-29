package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Enfermera {
    private String nombre;
    private String apellido;
    private int id; // Para almacenar el ID después de la inserción

    public Enfermera(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // Método para guardar en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO enfermeras (nombre, apellido) VALUES (?, ?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id"); // Obtener el ID generado
                System.out.println("Enfermera guardada con ID: " + id);
            }
        }
    }

    public int getId() {
        return id;
    }
}