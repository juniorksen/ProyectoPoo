package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaRadiologia {
    private String nombre;
    private int id; // Para almacenar el ID después de la inserción

    public AreaRadiologia(String nombre) {
        this.nombre = nombre;
    }

    // Método para guardar en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO areas_radiologia (nombre) VALUES (?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id"); // Obtener el ID generado
                System.out.println("Área de radiología guardada con ID: " + id);
            }
        }
    }

    public int getId() {
        return id;
    }
}
