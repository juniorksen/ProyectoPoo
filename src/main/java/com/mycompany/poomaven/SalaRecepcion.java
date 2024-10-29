package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaRecepcion {
    private String nombre;
    private List<Revista> revistas; // Composición: una sala de recepción contiene múltiples revistas
    private int id; // Para almacenar el ID después de la inserción

    public SalaRecepcion(String nombre) {
        this.nombre = nombre;
        this.revistas = new ArrayList<>();
    }

    // Método para agregar una revista a la sala de recepción
    public void agregarRevista(Revista revista) {
        revistas.add(revista);
    }

    // Método para guardar la sala de recepción y sus revistas en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO sala_recepcion (nombre) VALUES (?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id"); // Obtener el ID generado para la sala de recepción
                System.out.println("Sala de recepción guardada con ID: " + id);

                // Guardar las revistas asociadas
                for (Revista revista : revistas) {
                    revista.guardarEnBaseDatos(connection, id);
                }
            }
        }
    }

    public int getId() {
        return id;
    }
}
