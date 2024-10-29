package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;

class CoordinadorInvestigacion extends CoordinadorDeProyectos {
    private Connection connection;
    private String nivelEducativo;  // Almacena el nivel educativo del coordinador
    private String especialidad;     // Almacena la especialidad del coordinador

    // Constructor ajustado para coincidir con la clase abstracta CoordinadorDeProyectos
    public CoordinadorInvestigacion(String nombre, String nombreProyecto, String presupuesto, int proyectosActivos, 
                                    String nivelEducativo, String especialidad, Connection connection) {
        super(nombre, nombreProyecto, presupuesto, proyectosActivos);  
        this.nivelEducativo = nivelEducativo;
        this.especialidad = especialidad;
        this.connection = connection;
    }

    // Implementación de los métodos abstractos heredados
    @Override
    public void asignarRecursos() {
        System.out.println(nombre + " está asignando recursos para el proyecto: " + nombreProyecto);
        registrarActividad("Asignación de Recursos", "Asignando recursos para el proyecto: " + nombreProyecto);
    }

    @Override
    public void establecerCronograma() {
        System.out.println(nombre + " está estableciendo el cronograma del proyecto: " + nombreProyecto);
        registrarActividad("Establecimiento de Cronograma", "Estableciendo cronograma para el proyecto: " + nombreProyecto);
    }

    @Override
    public void evaluarProgreso() {
        System.out.println(nombre + " está evaluando el progreso del proyecto: " + nombreProyecto);
        registrarActividad("Evaluación de Progreso", "Evaluando el progreso del proyecto: " + nombreProyecto);
    }

    // Método para registrar cada actividad en la base de datos
    private void registrarActividad(String actividad, String detalle) {
        try {
            String insertQuery = "INSERT INTO gestion_proyectos (nombre, nombreProyecto, presupuesto, proyectosActivos, nivelEducativo, especialidad, actividad, detalle) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, this.nombre);
            preparedStatement.setString(2, this.nombreProyecto);
            preparedStatement.setString(3, this.presupuesto);
            preparedStatement.setInt(4, this.proyectosActivos);
            preparedStatement.setString(5, this.nivelEducativo);
            preparedStatement.setString(6, this.especialidad);
            preparedStatement.setString(7, actividad);
            preparedStatement.setString(8, detalle);
            preparedStatement.executeUpdate();
            System.out.println("Actividad de " + this.nombre + " registrada en la base de datos: " + actividad);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
