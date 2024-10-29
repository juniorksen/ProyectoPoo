package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class JefeComunicaciones extends PersonalComunicaciones {
    private Connection connection;
    private String nivelEducativo;
    private String especialidad;
    private String detalleActividad;  // Campo para almacenar el detalle de la actividad

    public JefeComunicaciones(String nombre, String canalDeComunicacion, String tipoDeMensaje, 
                              int proyectosActivos, String nivelEducativo, String especialidad, 
                              Connection connection) {
        super(nombre, canalDeComunicacion, tipoDeMensaje, proyectosActivos);
        this.nivelEducativo = nivelEducativo;
        this.especialidad = especialidad;
        this.connection = connection;
    }

    @Override
    public void redactarComunicado() {
        detalleActividad = "Redactando comunicado sobre " + tipoDeMensaje + " en el canal " + canalDeComunicacion;
        System.out.println(nombre + " está redactando un comunicado en el canal " + canalDeComunicacion + " sobre " + tipoDeMensaje + ".");
        registrarActividad("Redactar Comunicado", detalleActividad);
    }

    @Override
    public void gestionarRedesSociales() {
        detalleActividad = "Gestionando redes sociales para difundir mensajes sobre " + tipoDeMensaje;
        System.out.println(nombre + " está gestionando las redes sociales para difundir mensajes sobre " + tipoDeMensaje + ".");
        registrarActividad("Gestionar Redes Sociales", detalleActividad);
    }

    public String getNivelEducativo() {
        return nivelEducativo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getDetalleActividad() {
        return detalleActividad; // Método getter para el detalle
    }

    private void registrarActividad(String actividad, String detalle) {
        try {
            String insertQuery = "INSERT INTO jefe_comunicaciones (nombre, canalDeComunicacion, tipoDeMensaje, proyectosActivos, nivelEducativo, especialidad, actividad, detalle) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, this.nombre);
            preparedStatement.setString(2, this.canalDeComunicacion);
            preparedStatement.setString(3, this.tipoDeMensaje);
            preparedStatement.setInt(4, this.proyectosActivos);
            preparedStatement.setString(5, this.nivelEducativo);
            preparedStatement.setString(6, this.especialidad);
            preparedStatement.setString(7, actividad);
            preparedStatement.setString(8, detalle);
            preparedStatement.executeUpdate();
            System.out.println("Actividad de " + this.nombre + " registrada en la base de datos: " + actividad);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar actividad: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
