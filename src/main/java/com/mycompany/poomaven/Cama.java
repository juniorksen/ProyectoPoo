package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class Cama {
    protected String ubicacion;
    protected boolean ocupada;

    public Cama(String ubicacion) {
        this.ubicacion = ubicacion;
        this.ocupada = false;
    }

    public abstract void prepararCama();

    // Método para guardar en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO cama (ubicacion, ocupada) VALUES (?, ?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, ubicacion);
            pstmt.setBoolean(2, ocupada);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Cama guardada con ID: " + rs.getInt("id") + " en ubicación: " + ubicacion);
            }
        }
    }

    public void ocuparCama() {
        ocupada = true;
    }
}
