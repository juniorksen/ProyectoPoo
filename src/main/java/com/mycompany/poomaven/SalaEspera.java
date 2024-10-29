package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaEspera {
    private String nombre;
    private int capacidad;
    private int id; // Para almacenar el ID después de la inserción

    public SalaEspera(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    // Método para guardar en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO salas_espera (nombre, capacidad) VALUES (?, ?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, capacidad);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id"); // Obtener el ID generado
                System.out.println("Sala de espera guardada con ID: " + id);
            }
        }
    }

    public int getId() {
        return id;
    }
}
