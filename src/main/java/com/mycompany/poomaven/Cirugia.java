package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cirugia {
    private String tipo;
    private String fecha;
    private int quirofanoId;

    public Cirugia(String tipo, String fecha, int quirofanoId) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.quirofanoId = quirofanoId;
    }

    // Método para guardar en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO cirugias (tipo, fecha, quirofano_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, tipo);
            pstmt.setString(2, fecha);
            pstmt.setInt(3, quirofanoId);
            pstmt.executeUpdate();
            System.out.println("Cirugía " + tipo + " guardada en la base de datos.");
        }
    }
}
