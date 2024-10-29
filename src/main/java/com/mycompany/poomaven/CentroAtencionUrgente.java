package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CentroAtencionUrgente {
    private String nombre;
    private List<ProtocoloAtencion> protocolos; // Composición: un centro de atención urgente contiene múltiples protocolos
    private int id; // Para almacenar el ID después de la inserción

    public CentroAtencionUrgente(String nombre) {
        this.nombre = nombre;
        this.protocolos = new ArrayList<>();
    }

    // Método para agregar un protocolo al centro de atención urgente
    public void agregarProtocolo(ProtocoloAtencion protocolo) {
        protocolos.add(protocolo);
    }

    // Método para guardar el centro de atención urgente y sus protocolos en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO centro_atencion_urgente (nombre) VALUES (?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id"); // Obtener el ID generado para el centro de atención
                System.out.println("Centro de atención urgente guardado con ID: " + id);

                // Guardar los protocolos asociados
                for (ProtocoloAtencion protocolo : protocolos) {
                    protocolo.guardarEnBaseDatos(connection, id);
                }
            }
        }
    }

    public int getId() {
        return id;
    }
}

