package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class ProcedimientoQuirurgico {
    protected String nombre;
    protected String descripcion;

    public ProcedimientoQuirurgico(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public abstract void realizar(); // Método polimórfico

    // Método para guardar en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO procedimiento (nombre, descripcion) VALUES (?, ?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, descripcion);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Procedimiento guardado con ID: " + rs.getInt("id") + ", Nombre: " + nombre);
            }
        }
    }
}
