package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Servicio {
    private String nombre;
    private double costo;
    private String personalEncargado;
    private int duracion; // Duración en minutos

    public Servicio(String nombre, double costo, String personalEncargado, int duracion) {
        this.nombre = nombre;
        this.costo = costo;
        this.personalEncargado = personalEncargado;
        this.duracion = duracion;
    }

    // Método para guardar el servicio en la base de datos
    public void guardarEnBaseDatos(Connection connection, int unidadCuidadoId) throws SQLException {
        String insertQuery = "INSERT INTO servicio (nombre, costo, personal_encargado, duracion, unidad_cuidado_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            pstmt.setDouble(2, costo);
            pstmt.setString(3, personalEncargado);
            pstmt.setInt(4, duracion);
            pstmt.setInt(5, unidadCuidadoId);
            pstmt.executeUpdate();
            System.out.println("Servicio " + nombre + " guardado en la base de datos.");
        }
    }
}
