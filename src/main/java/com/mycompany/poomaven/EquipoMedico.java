    package com.mycompany.poomaven;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.SQLException;

    public class EquipoMedico {
        private String tipo;
        private String fabricante;

        public EquipoMedico(String tipo, String fabricante) {
            this.tipo = tipo;
            this.fabricante = fabricante;
        }

        // Método para guardar el equipo médico en la base de datos asociado a una UCI
        public void guardarEnBaseDatos(Connection connection, int uciId) throws SQLException {
            String insertQuery = "INSERT INTO equipo_medico (tipo, fabricante, uci_id) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
                pstmt.setString(1, tipo);
                pstmt.setString(2, fabricante);
                pstmt.setInt(3, uciId);
                pstmt.executeUpdate();
                System.out.println("Equipo médico guardado en la base de datos.");
            }
        }
    }
