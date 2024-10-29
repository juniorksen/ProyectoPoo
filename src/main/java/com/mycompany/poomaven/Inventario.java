package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private String nombre;
    private List<Suministro> suministros; // Composición: un inventario contiene múltiples suministros
    private int id; // Para almacenar el ID después de la inserción

    public Inventario(String nombre) {
        this.nombre = nombre;
        this.suministros = new ArrayList<>();
    }

    // Método para agregar un suministro al inventario
    public void agregarSuministro(Suministro suministro) {
        suministros.add(suministro);
    }

    // Método para guardar el inventario y sus suministros en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO inventario (nombre) VALUES (?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id"); // Obtener el ID generado para el inventario
                System.out.println("Inventario guardado con ID: " + id);

                // Guardar los suministros asociados
                for (Suministro suministro : suministros) {
                    suministro.guardarEnBaseDatos(connection, id);
                }
            }
        }
    }

    public int getId() {
        return id;
    }
}
