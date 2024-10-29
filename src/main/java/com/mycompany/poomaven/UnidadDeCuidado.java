package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnidadDeCuidado {
    private String nombre;
    private String direccion;
    private int id; // Para almacenar el ID después de la inserción
    private List<Servicio> servicios; // Composición: una Unidad de Cuidado tiene varios Servicios

    public UnidadDeCuidado(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.servicios = new ArrayList<>(); // Inicializar la lista de servicios
    }

    public void agregarServicio(Servicio servicio) {
        this.servicios.add(servicio);
    }

    // Método para guardar la Unidad de Cuidado y sus Servicios en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO unidad_de_cuidado (nombre, direccion) VALUES (?, ?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, direccion);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id"); // Obtener el ID generado
                System.out.println("Unidad de Cuidado guardada con ID: " + id);

                // Guardar cada servicio asociado
                for (Servicio servicio : servicios) {
                    servicio.guardarEnBaseDatos(connection, id);
                }
            }
        }
    }

    public int getId() {
        return id;
    }
}
