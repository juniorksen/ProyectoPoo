package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;

class GestorMantenimientoPlanta extends GestorMantenimiento {
    private String nivelEducativo;
    private String especialidadDocente;
    private Connection connection;
    
    // Nuevas propiedades para actividad y detalle
    private String actividad;
    private String detalle;

    public GestorMantenimientoPlanta(String nombre, String areaResponsabilidad, String tipoDeEquipo, int proyectosActivos, String nivelEducativo, String especialidadDocente, Connection connection) {
        super(nombre, areaResponsabilidad, tipoDeEquipo, proyectosActivos);
        this.nivelEducativo = nivelEducativo;
        this.especialidadDocente = especialidadDocente;
        this.connection = connection;
    }

    // Getters
    public String getNivelEducativo() {
        return nivelEducativo;
    }

    public String getEspecialidadDocente() {
        return especialidadDocente;
    }
    
    // Getters para actividad y detalle
    public String getActividad() {
        return actividad != null ? actividad : "Actividad no disponible";
    }

    public String getDetalle() {
        return detalle != null ? detalle : "Detalles no disponibles";
    }
    
    // Setters para actividad y detalle
    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public void programarMantenimiento() {
        System.out.println(nombre + " está programando mantenimiento para el equipo de tipo " + tipoDeEquipo + ".");
        registrarActividad("Programar Mantenimiento", "Programando mantenimiento para el equipo de tipo " + tipoDeEquipo);
    }

    @Override
    public void repararEquipo() {
        System.out.println(nombre + " está reparando el equipo de tipo " + tipoDeEquipo + ".");
        registrarActividad("Reparar Equipo", "Reparando el equipo de tipo " + tipoDeEquipo);
    }

    // Método para registrar la actividad en la base de datos
    private void registrarActividad(String actividad, String detalle) {
        try {
            String insertQuery = "INSERT INTO gestion_mantenimiento (nombre, areaResponsabilidad, tipoDeEquipo, proyectosActivos, nivelEducativo, especialidadDocente, actividad, detalle) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, this.nombre);
            preparedStatement.setString(2, this.areaResponsabilidad);
            preparedStatement.setString(3, this.tipoDeEquipo);
            preparedStatement.setInt(4, this.proyectosActivos);
            preparedStatement.setString(5, this.nivelEducativo);
            preparedStatement.setString(6, this.especialidadDocente);
            preparedStatement.setString(7, actividad);
            preparedStatement.setString(8, detalle);
            preparedStatement.executeUpdate();
            System.out.println("Actividad de " + this.nombre + " registrada en la base de datos: " + actividad);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    