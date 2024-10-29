package com.mycompany.poomaven;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CapacitacionMedicaDAOImpl implements CapacitacionMedicaDAO {
    private final Connection connection;

    public CapacitacionMedicaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<CapacitacionMedico> getCapacitacionesByProyectosActivos(int proyectosActivos) {
        List<CapacitacionMedico> capacitaciones = new ArrayList<>();
        String query = "SELECT * FROM capacitacion_medico WHERE proyectosActivos = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, proyectosActivos);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CapacitacionMedico capacitacion = new CapacitacionMedico(
                    resultSet.getString("nombre"),
                    resultSet.getString("campoInvestigacion"),
                    resultSet.getInt("proyectosActivos"),
                    resultSet.getString("nivelEducativo"),
                    resultSet.getString("especialidadDocente"),
                    connection // Pasar la conexión aquí
                );
                capacitaciones.add(capacitacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return capacitaciones;
    }

    @Override
    public List<CapacitacionMedico> getAllCapacitaciones() {
        List<CapacitacionMedico> capacitaciones = new ArrayList<>();
        String query = "SELECT * FROM capacitacion_medico";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CapacitacionMedico capacitacion = new CapacitacionMedico(
                    resultSet.getString("nombre"),
                    resultSet.getString("campoInvestigacion"),
                    resultSet.getInt("proyectosActivos"),
                    resultSet.getString("nivelEducativo"),
                    resultSet.getString("especialidadDocente"),
                    connection // Pasar la conexión aquí también
                );
                capacitaciones.add(capacitacion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return capacitaciones;
    }
}
