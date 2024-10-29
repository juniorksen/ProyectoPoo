package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoriaClinica {
    private String paciente;
    private Date fechaApertura; // Cambiar a tipo Date
    private int id; // Para almacenar el ID después de la inserción
    private Diagnostico diagnostico; // Composición: una Historia Clínica contiene un Diagnóstico

    public HistoriaClinica(String paciente, String fechaApertura, String descripcionDiagnostico, String medico) {
        this.paciente = paciente;
        this.fechaApertura = Date.valueOf(fechaApertura); // Convertir String a Date
        // Composición: un diagnóstico es parte de la Historia Clínica
        this.diagnostico = new Diagnostico(descripcionDiagnostico, medico);
    }

    // Método para guardar la Historia Clínica en la base de datos junto con su Diagnóstico
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO historia_clinica (paciente, fecha_apertura) VALUES (?, ?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, paciente);
            pstmt.setDate(2, fechaApertura); // Establecer Date directamente
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id"); // Obtener el ID generado para la historia clínica
                System.out.println("Historia Clínica guardada con ID: " + id);

                // Guardar el diagnóstico asociado
                diagnostico.guardarEnBaseDatos(connection, id);
            }
        }
    }

    public int getId() {
        return id;
    }
}
