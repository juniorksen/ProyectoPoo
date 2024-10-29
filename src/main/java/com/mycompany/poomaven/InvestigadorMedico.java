package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Date;


class InvestigadorMedico extends Investigador {
    private Connection connection;

    public InvestigadorMedico(String nombre, String campoInvestigacion, int proyectosActivos, Connection connection) {
        super(nombre, campoInvestigacion, proyectosActivos);
        this.connection = connection;
    }

    @Override
    public void disenarEstudio() {
        System.out.println(getNombre() + " está diseñando un nuevo estudio en el campo de " + getCampoInvestigacion());
        registrarActividad("Diseñar Estudio", "Diseñando un estudio en el campo de " + getCampoInvestigacion());
    }

    @Override
    public void analizarDatos() {
        System.out.println(getNombre() + " está analizando los datos de los proyectos actuales.");
        registrarActividad("Analizar Datos", "Analizando datos de los proyectos activos.");
    }

    @Override
    public void publicarInforme() {
        System.out.println(getNombre() + " ha publicado un informe con los resultados de su investigación.");
        registrarActividad("Publicar Informe", "Informe publicado en el campo de " + getCampoInvestigacion());
    }

    // Método para registrar cada actividad en la base de datos
    private void registrarActividad(String actividad, String detalle) {
        try {
            String insertQuery = "INSERT INTO investigador (nombre, campoInvestigacion, proyectosActivos, actividad, detalle) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, getNombre());
            preparedStatement.setString(2, getCampoInvestigacion());
            preparedStatement.setInt(3, getProyectosActivos());
            preparedStatement.setString(4, actividad);
            preparedStatement.setString(5, detalle);
            preparedStatement.executeUpdate();
            System.out.println("Investigador " + getNombre() + " y actividad registrada en la base de datos: " + actividad);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
