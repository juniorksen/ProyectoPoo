package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServicioEmergencia {
    private String descripcion;
    private String fecha;
    private int carroAmbulanciaId;

    public ServicioEmergencia(String descripcion, String fecha, int carroAmbulanciaId) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.carroAmbulanciaId = carroAmbulanciaId;
    }

    // Método para guardar en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO servicios_emergencia (descripcion, fecha, carro_ambulancia_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, descripcion);
            pstmt.setString(2, fecha);
            pstmt.setInt(3, carroAmbulanciaId);
            pstmt.executeUpdate();
            System.out.println("ServicioEmergencia guardado en la base de datos.");
        }
    }
}
