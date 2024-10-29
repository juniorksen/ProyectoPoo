package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Clase concreta que extiende de AsesorLegal
class AbogadoLaboral extends AsesorLegal {
    private Connection connection;
    private String nivelEducativo;

    // Constructor adaptado para coincidir con la clase AsesorLegal
    public AbogadoLaboral(String nombre, String especialidadLegal, int proyectosActivos,
                          String nivelEducativo, Connection connection) {
        super(nombre, especialidadLegal, proyectosActivos);  // Ajustar según el constructor de la clase abstracta
        this.nivelEducativo = nivelEducativo;
        this.connection = connection;
    }

    // Implementación de los métodos abstractos heredados de AsesorLegal
    @Override
    public void analizarNormativa() {
        System.out.println(nombre + " está analizando la normativa en la especialidad: " + especialidadLegal);
        registrarActividad("Análisis de Normativa", "Analizando normativa en la especialidad de " + especialidadLegal);
    }

    @Override
    public void asesorarDirectivos() {
        System.out.println(nombre + " está asesorando a los directivos en: " + especialidadLegal);
        registrarActividad("Asesoría a Directivos", "Asesorando a los directivos en la especialidad de " + especialidadLegal);
    }

    // Método para registrar cada actividad en la base de datos
    private void registrarActividad(String actividad, String detalle) {
        String insertQuery = "INSERT INTO gestion_proyectos (nombre, especialidadLegal, proyectosActivos, nivelEducativo, actividad, detalle) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, this.nombre);
            preparedStatement.setString(2, this.especialidadLegal);
            preparedStatement.setInt(3, this.proyectosActivos);
            preparedStatement.setString(4, this.nivelEducativo);
            preparedStatement.setString(5, actividad);
            preparedStatement.setString(6, detalle);
            preparedStatement.executeUpdate();
            System.out.println("Actividad de " + this.nombre + " registrada en la base de datos: " + actividad);
        } catch (SQLException e) {
            System.err.println("Error al registrar actividad: " + e.getMessage());
        }
    }
}
