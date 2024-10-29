package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Diagnostico {
    private String descripcion;
    private String medico;

    public Diagnostico(String descripcion, String medico) {
        this.descripcion = descripcion;
        this.medico = medico;
    }

    // Método para guardar el diagnóstico en la base de datos asociado a una Historia Clínica
    public void guardarEnBaseDatos(Connection connection, int historiaClinicaId) throws SQLException {
        String insertQuery = "INSERT INTO diagnostico (descripcion, medico, historia_clinica_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, descripcion);
            pstmt.setString(2, medico);
            pstmt.setInt(3, historiaClinicaId);
            pstmt.executeUpdate();
            System.out.println("Diagnóstico guardado en la base de datos.");
        }
    }
}
