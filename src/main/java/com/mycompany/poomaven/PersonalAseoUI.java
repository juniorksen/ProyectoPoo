package com.mycompany.poomaven;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalAseoUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private Connection connection;
    private JTextField searchField;
    private JButton searchButton;

    public PersonalAseoUI(Connection connection) {
        this.connection = connection;

        // Verificar la conexión a la base de datos
        try {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Conexión a la base de datos exitosa.");
            } else {
                System.out.println("Conexión a la base de datos fallida.");
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la conexión: " + e.getMessage());
        }

        setTitle("Personal de Aseo");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear la tabla y el modelo
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Cédula");
        model.addColumn("Área");
        model.addColumn("Correo");
        table = new JTable(model);
        table.setFillsViewportHeight(true);

        // Añadir la tabla al panel de desplazamiento
        add(new JScrollPane(table), BorderLayout.CENTER);

       

        // Cargar los datos al abrir la interfaz
        cargarDatos();

        // Aseguramos que la ventana se muestre
        setVisible(true);
    }

    private void cargarDatos() {
        model.setRowCount(0); // Limpiar datos previos
        String sql = "SELECT id, nombre, cedula, area, correo FROM personal_aseo"; // Ajusta la consulta según tu tabla

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("Ejecutando consulta: " + sql); // Imprimir consulta para depuración

            while (resultSet.next()) {
                model.addRow(new Object[]{
                    resultSet.getInt("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("cedula"),
                    resultSet.getString("area"),
                    resultSet.getString("correo")
                });
            }

            System.out.println("Datos cargados: " + model.getRowCount()); // Verificar cuántas filas se han cargado
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener datos del personal de aseo: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarPorCedula() {
        String cedulaBuscada = searchField.getText().trim(); // Obtener la cédula del campo de búsqueda
        model.setRowCount(0); // Limpiar datos previos

        // Comprobar si el campo de búsqueda está vacío
        if (cedulaBuscada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una cédula para buscar.",
                    "Campo vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "SELECT id, nombre, cedula, area, correo FROM personal_aseo WHERE cedula = ?"; // Consulta parametrizada

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cedulaBuscada); // Establecer el valor de la cédula
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                model.addRow(new Object[]{
                    resultSet.getInt("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("cedula"),
                    resultSet.getString("area"),
                    resultSet.getString("correo")
                });
            }

            System.out.println("Datos cargados: " + model.getRowCount()); // Verificar cuántas filas se han cargado
        } catch (SQLException e) {
          
    }
    }
}
