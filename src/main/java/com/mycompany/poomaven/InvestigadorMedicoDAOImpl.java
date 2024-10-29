package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class InvestigadorMedicoDAOImpl implements InvestigadorMedicoDAO {
    private Connection connection;

    public InvestigadorMedicoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void guardarInvestigador(Investigador investigador) throws SQLException {
        String sql = "INSERT INTO investigador (nombre, campoInvestigacion, proyectosActivos) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, investigador.getNombre());
            stmt.setString(2, investigador.getCampoInvestigacion());
            stmt.setInt(3, investigador.getProyectosActivos());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<InvestigadorMedico> obtenerTodosInvestigadores() throws SQLException {
        List<InvestigadorMedico> investigadores = new ArrayList<>();
        String sql = "SELECT nombre, campoInvestigacion, proyectosActivos FROM investigador";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String campoInvestigacion = rs.getString("campoInvestigacion");
                int proyectosActivos = rs.getInt("proyectosActivos");
                investigadores.add(new InvestigadorMedico(nombre, campoInvestigacion, proyectosActivos, connection));
            }
        }
        return investigadores;
    }
}
