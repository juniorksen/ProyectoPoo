package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Clase concreta que extiende de AuditorInterno
class AuditorFinanciero extends AuditorInterno {
    private Connection connection;
    private String nivelEducativo;

    // Constructor adaptado para coincidir con la clase AuditorInterno
    public AuditorFinanciero(String nombre, String areaDeAuditoria, int proyectosActivos,
                             String nivelEducativo, Connection connection) {
        super(nombre, areaDeAuditoria, proyectosActivos);  // Ajustar según el constructor de la clase abstracta
        this.nivelEducativo = nivelEducativo;
        this.connection = connection;
    }

    // Implementación de los métodos abstractos heredados de AuditorInterno
    @Override
    public void realizarAuditoria() {
        System.out.println(nombre + " está realizando una auditoría en el área: " + areaDeAuditoria);
        registrarActividad("Auditoría", "Realizando auditoría en el área de " + areaDeAuditoria);
    }

    @Override
    public void emitirReporte() {
        System.out.println(nombre + " está emitiendo un reporte sobre el área: " + areaDeAuditoria);
        registrarActividad("Emisión de Reporte", "Emitiendo reporte sobre el área de " + areaDeAuditoria);
    }

    // Método para registrar cada actividad en la base de datos
    private void registrarActividad(String actividad, String detalle) {
        String insertQuery = "INSERT INTO gestion_proyectos (nombre, areaDeAuditoria, proyectosActivos, nivelEducativo, actividad, detalle) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, this.nombre);
            preparedStatement.setString(2, this.areaDeAuditoria);
            preparedStatement.setInt(3, this.proyectosActivos);
            preparedStatement.setString(4, this.nivelEducativo);
            preparedStatement.setString(5, actividad);
            preparedStatement.setString(6, detalle);
            preparedStatement.executeUpdate();
            System.out.println("Actividad de " + this.nombre + " registrada en la base de datos: " + actividad);
        } catch (SQLException e) {
            System.err.println("Error al registrar actividad: " + e.getMessage());
        }
    }
}
