package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UnidadCuidadosIntensivos {
    private String nombre;
    private String ubicacion;
    private int id; // Para almacenar el ID después de la inserción
    private EquipoMedico equipoMedico; // Composición: una UCI tiene equipo médico

    public UnidadCuidadosIntensivos(String nombre, String ubicacion, String tipoEquipo, String fabricante) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        // Composición: se crea un equipo médico como parte de la UCI
        this.equipoMedico = new EquipoMedico(tipoEquipo, fabricante);
    }

    // Método para guardar la UCI en la base de datos junto con su equipo médico
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO unidad_cuidados_intensivos (nombre, ubicacion) VALUES (?, ?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, ubicacion);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id"); // Obtener el ID generado para la UCI
                System.out.println("UCI guardada con ID: " + id);

                // Guardar el equipo médico asociado
                equipoMedico.guardarEnBaseDatos(connection, id);
            }
        }
    }

    public int getId() {
        return id;
    }
}
