package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;

class AdministradorRedes extends EspecialistaTI {
    private Connection connection;
    private String nivelEducativo;  // Agregado para almacenar el nivel educativo
    private String especialidad;     // Agregado para almacenar la especialidad en redes

    // Constructor ajustado para coincidir con la clase abstracta EspecialistaTI
    public AdministradorRedes(String nombre, String nivelAcceso, String herramientasUtilizadas, int proyectosActivos, String nivelEducativo, String especialidad, Connection connection) {
        super(nombre, nivelAcceso, herramientasUtilizadas, proyectosActivos);  
        this.nivelEducativo = nivelEducativo;
        this.especialidad = especialidad;
        this.connection = connection;
    }

    @Override
    public void mantenerSistema() {
        System.out.println(nombre + " está realizando mantenimiento del sistema utilizando las herramientas: " + herramientasUtilizadas + ".");
        registrarActividad("Mantenimiento del Sistema", "Realizando mantenimiento del sistema con herramientas: " + herramientasUtilizadas);
    }

    @Override
    public void resolverIncidente() {
        System.out.println(nombre + " está resolviendo un incidente crítico con nivel de acceso: " + nivelAcceso + ".");
        registrarActividad("Resolución de Incidente", "Resolviendo un incidente con nivel de acceso: " + nivelAcceso);
    }

    // Método para registrar cada actividad en la base de datos
    private void registrarActividad(String actividad, String detalle) {
        try {
            String insertQuery = "INSERT INTO gestion_comunicaciones (nombre, nivelAcceso, herramientasUtilizadas, proyectosActivos, nivelEducativo, especialidad, actividad, detalle) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, this.nombre);
            preparedStatement.setString(2, this.nivelAcceso);
            preparedStatement.setString(3, this.herramientasUtilizadas);
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
