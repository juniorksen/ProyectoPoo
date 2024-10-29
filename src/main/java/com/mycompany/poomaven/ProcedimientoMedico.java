package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class ProcedimientoMedico {
    protected String tipo;
    protected int duracion; // Duración en minutos

    public ProcedimientoMedico(String tipo, int duracion) {
        this.tipo = tipo;
        this.duracion = duracion;
    }

    public abstract void realizarProcedimiento(); // Método polimórfico

    // Método para guardar en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO procedimiento_medico (tipo, duracion) VALUES (?, ?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, tipo);
            pstmt.setInt(2, duracion);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Procedimiento médico guardado con ID: " + rs.getInt("id") + " y tipo: " + tipo);
            }
        }
    }
}
