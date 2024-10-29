package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorMedicoDAOImpl implements InstructorMedicoDAO {
    private Connection connection;

    public InstructorMedicoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    
    @Override
public List<InstructorMedico> obtenerTodosInstructores() {
    List<InstructorMedico> instructores = new ArrayList<>();
    String sql = "SELECT * FROM instructor";

    try (PreparedStatement stmt = connection.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            int id = rs.getInt("id"); // Obtener el ID de la base de datos
            String nombre = rs.getString("nombre");
            String nivelEducativo = rs.getString("nivelEducativo");
            String especialidadDocente = rs.getString("especialidadDocente");
            int proyectosActivos = rs.getInt("proyectosActivos");
            String detalle = rs.getString("detalle"); // Capturando el detalle

            // Crear una instancia de InstructorMedico
            InstructorMedico instructor = new InstructorMedico(id, nombre, nivelEducativo, especialidadDocente, proyectosActivos, detalle, connection);
            instructores.add(instructor);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return instructores;
}



    @Override
    public void agregarInstructor(InstructorMedico instructor) {
        String sql = "INSERT INTO instructor (nombre, nivelEducativo, especialidadDocente, proyectosActivos, detalle) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, instructor.getNombre());
            stmt.setString(2, instructor.getNivelEducativo());
            stmt.setString(3, instructor.getEspecialidadDocente());
            stmt.setInt(4, instructor.getProyectosActivos());
            stmt.setString(5, instructor.getDetalle()); // Asegúrate de que detalle esté disponible

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarInstructor(int id) {
        String sql = "DELETE FROM instructor WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarInstructor(InstructorMedico instructor) {
        String sql = "UPDATE instructor SET nombre = ?, nivelEducativo = ?, especialidadDocente = ?, proyectosActivos = ?, detalle = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, instructor.getNombre());
            stmt.setString(2, instructor.getNivelEducativo());
            stmt.setString(3, instructor.getEspecialidadDocente());
            stmt.setInt(4, instructor.getProyectosActivos());
            stmt.setString(5, instructor.getDetalle()); // Asegúrate de que detalle esté disponible
            stmt.setInt(6, instructor.getId()); // Asumiendo que tienes un método getId() en InstructorMedico

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
