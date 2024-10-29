package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;

class InstructorMedico extends Instructor {
    private Connection connection;
    private int id; // ID del instructor
    private String detalle; // Detalle de la actividad

    public InstructorMedico(int id, String nombre, String nivelEducativo, String especialidadDocente, int proyectosActivos, String detalle, Connection connection) {
        super(nombre, nivelEducativo, especialidadDocente, proyectosActivos);
        this.connection = connection;
        this.id = id; // Inicializa el ID
        this.detalle = detalle; // Inicializa el detalle
    }

    // Método para obtener el ID
    public int getId() {
        return id;
    }

    // Método para obtener el detalle
    public String getDetalle() {
        return detalle;
    }

    @Override
    public void planificarCurso() {
        System.out.println(nombre + " está planificando un curso en su especialidad de " + especialidadDocente + ".");
        registrarActividad("Planificar Curso", "Planificando un curso en la especialidad de " + especialidadDocente);
    }

    @Override
    public void evaluarEstudiantes() {
        System.out.println(nombre + " está evaluando a los estudiantes en el curso de " + especialidadDocente + ".");
        registrarActividad("Evaluar Estudiantes", "Evaluando estudiantes en la especialidad de " + especialidadDocente);
    }

    @Override
    public void publicarNotas() {
        System.out.println(nombre + " ha publicado las notas para el curso en " + especialidadDocente + ".");
        registrarActividad("Publicar Notas", "Publicación de notas en el curso de " + especialidadDocente);
    }

    private void registrarActividad(String actividad, String detalle) {
        try {
            String insertQuery = "INSERT INTO instructor (nombre, nivelEducativo, especialidadDocente, proyectosActivos, actividad, detalle) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, this.nombre);
            preparedStatement.setString(2, this.nivelEducativo);
            preparedStatement.setString(3, this.especialidadDocente);
            preparedStatement.setInt(4, this.proyectosActivos);
            preparedStatement.setString(5, actividad);
            preparedStatement.setString(6, detalle);
            preparedStatement.executeUpdate();
            System.out.println("Instructor " + this.nombre + " y actividad registrada en la base de datos: " + actividad);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
