package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class RecetaMedica {
    protected String tipoReceta;
    protected String medicamento;

    public RecetaMedica(String tipoReceta, String medicamento) {
        this.tipoReceta = tipoReceta;
        this.medicamento = medicamento;
    }

    public abstract void prescribirTratamiento(); // Método polimórfico

    // Método para guardar en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO receta_medica (tipo_receta, medicamento) VALUES (?, ?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, tipoReceta);
            pstmt.setString(2, medicamento);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Receta médica guardada con ID: " + rs.getInt("id") + " y tipo: " + tipoReceta);
            }
        }
    }
}
