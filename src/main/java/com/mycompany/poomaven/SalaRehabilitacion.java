package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaRehabilitacion {
    private String nombre; // Ejemplo: "Sala de Fisioterapia"
    private String ubicacion;
    private int id; // Para almacenar el ID después de la inserción
    private List<ProgramaRehabilitacion> programasRehabilitacion; // Composición

    public SalaRehabilitacion(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.programasRehabilitacion = new ArrayList<>(); // Inicializar la lista de programas
    }

    // Método para agregar un programa de rehabilitación a la sala
    public void agregarProgramaRehabilitacion(String nombrePrograma, String descripcion) {
        ProgramaRehabilitacion nuevoPrograma = new ProgramaRehabilitacion(nombrePrograma, descripcion);
        programasRehabilitacion.add(nuevoPrograma);
    }

    // Método para guardar la sala en la base de datos junto con sus programas
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO sala_rehabilitacion (nombre, ubicacion) VALUES (?, ?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, ubicacion);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id"); // Obtener el ID generado para la sala
                System.out.println("Sala de Rehabilitación guardada con ID: " + id);

                // Guardar cada programa asociado
                for (ProgramaRehabilitacion programa : programasRehabilitacion) {
                    programa.guardarEnBaseDatos(connection, id);
                }
            }
        }
    }

    public int getId() {
        return id;
    }
}
