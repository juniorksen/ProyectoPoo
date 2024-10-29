package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EstudioRadiologico {
    private String tipoEstudio;
    private String fecha;
    private int areaRadiologiaId;

    public EstudioRadiologico(String tipoEstudio, String fecha, int areaRadiologiaId) {
        this.tipoEstudio = tipoEstudio;
        this.fecha = fecha;
        this.areaRadiologiaId = areaRadiologiaId;
    }

    // Método para guardar en la base de datos
    public void guardarEnBaseDatos(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO estudios_radiologicos (tipo_estudio, fecha, area_radiologia_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, tipoEstudio);
            pstmt.setString(2, fecha);
            pstmt.setInt(3, areaRadiologiaId);
            pstmt.executeUpdate();
            System.out.println("Estudio radiológico guardado en la base de datos.");
        }
    }
}
