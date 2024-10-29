package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class CoordinadorBienestar extends ResponsableDeBienestar {
    private Connection connection;
    private String nivelEducativo;  
    private String especialidad;     

    // Constructor ajustado para coincidir con la clase abstracta ResponsableDeBienestar
    public CoordinadorBienestar(String nombre, String edad, int proyectosActivos, 
                                String nivelEducativo, String especialidad, Connection connection) {
        super(nombre, edad, proyectosActivos);  
        this.nivelEducativo = nivelEducativo;
        this.especialidad = especialidad;
        this.connection = connection;
    }

    // Implementación de los métodos abstractos heredados
    @Override
    public void organizarActividadesRecreativas() {
        System.out.println(nombre + " está organizando actividades recreativas.");
        registrarActividad("Organización de Actividades Recreativas", "Organizando actividades recreativas.");
    }

    @Override
    public void ofrecerAsesoramiento() {
        System.out.println(nombre + " está ofreciendo asesoramiento.");
        registrarActividad("Asesoramiento Ofrecido", "Ofreciendo asesoramiento a los participantes.");
    }

    // Método para registrar cada actividad en la base de datos
    private void registrarActividad(String actividad, String detalle) {
        String insertQuery = "INSERT INTO gestion_proyectos (nombre, edad, proyectosActivos, nivelEducativo, especialidad, actividad, detalle) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, this.nombre);
            preparedStatement.setString(2, this.edad);
            preparedStatement.setInt(3, this.proyectosActivos);
            preparedStatement.setString(4, this.nivelEducativo);
            preparedStatement.setString(5, this.especialidad);
            preparedStatement.setString(6, actividad);
            preparedStatement.setString(7, detalle);
            preparedStatement.executeUpdate();
            System.out.println("Actividad de " + this.nombre + " registrada en la base de datos: " + actividad);
        } catch (SQLException e) {
            System.err.println("Error al registrar actividad: " + e.getMessage());
        }
    }
}
