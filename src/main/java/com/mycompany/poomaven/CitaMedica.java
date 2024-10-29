package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CitaMedica {
    private String fecha;
    private String hora;
    private int salaEsperaId;

    public CitaMedica(String fecha, String hora, int salaEsperaId) {
        this.fecha = fecha;
        this.hora = hora;
        this.salaEsperaId = salaEsperaId;
    }

    // Método para guardar en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO citas_medicas (fecha, hora, sala_espera_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, fecha);
            pstmt.setString(2, hora);
            pstmt.setInt(3, salaEsperaId);
            pstmt.executeUpdate();
            System.out.println("Cita médica guardada en la base de datos.");
        }
    }
}
