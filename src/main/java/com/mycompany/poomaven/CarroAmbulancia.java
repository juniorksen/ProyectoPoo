package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarroAmbulancia {
    private String placa;
    private String modelo;
    private int id; // ID después de la inserción

    public CarroAmbulancia(String placa, String modelo) {
        this.placa = placa;
        this.modelo = modelo;
    }

    // Método para guardar en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO carros_ambulancia (placa, modelo) VALUES (?, ?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, placa);
            pstmt.setString(2, modelo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id"); // Obtener el ID generado
                System.out.println("CarroAmbulancia guardada con ID: " + id);
            }
        }
    }

    public int getId() {
        return id;
    }
}
