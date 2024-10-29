package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Turno {
    private String horaInicio;
    private String horaFin;
    private int enfermeraId;

    public Turno(String horaInicio, String horaFin, int enfermeraId) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.enfermeraId = enfermeraId;
    }

    // Método para guardar en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO turnos (hora_inicio, hora_fin, enfermera_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, horaInicio);
            pstmt.setString(2, horaFin);
            pstmt.setInt(3, enfermeraId);
            pstmt.executeUpdate();
            System.out.println("Turno de " + horaInicio + " a " + horaFin + " guardado en la base de datos.");
        }
    }
}