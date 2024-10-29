package com.mycompany.poomaven;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class ConductorambulanciaUI extends JFrame {
    private Connection connection;
    private JTable table;
    private DefaultTableModel model;
    private JTextField filterField;

    public ConductorambulanciaUI(Connection connection) {
        this.connection = connection;
        setTitle("Conductor de Ambulancia");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración de la tabla
        model = new DefaultTableModel(new String[]{"Turno Semana", "Tiempo Trabajado", "Hora Inicio", "Hora Fin", "Cédula", "Nombre", "Apellido", "Correo"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel para el campo de texto del filtro
        JPanel filterPanel = new JPanel();
        filterField = new JTextField(15);
        JButton filterButton = new JButton("Filtrar");
        filterButton.addActionListener(this::filterData);
        filterPanel.add(new JLabel("Filtrar por Cédula:"));
        filterPanel.add(filterField);
        filterPanel.add(filterButton);
        add(filterPanel, BorderLayout.NORTH);

        // Cargar los datos inicialmente
        loadData();

        setVisible(true);
    }

    private void loadData() {
        if (!checkConnection()) {
            return;
        }

        model.setRowCount(0); // Limpiar datos previos
        String query = "SELECT * FROM conductorambulancia";

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("turnossemana"),
                    rs.getString("tiempotrabajado"),
                    rs.getString("horainicio"),
                    rs.getString("horafin"),
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("correo")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos del conductor de ambulancia.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void filterData(ActionEvent e) {
        if (!checkConnection()) {
            return;
        }

        String cedulaFiltrada = filterField.getText().trim();
        model.setRowCount(0); // Limpiar datos previos
        String query = "SELECT * FROM conductorambulancia WHERE cedula LIKE ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "%" + cedulaFiltrada + "%"); // Filtrado por cédula
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("turnossemana"),
                        rs.getString("tiempotrabajado"),
                        rs.getString("horainicio"),
                        rs.getString("horafin"),
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo")
                    });
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos filtrados del conductor de ambulancia.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private boolean checkConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                String url = "jdbc:postgresql://localhost/poomaven_db";
                String user = "postgres";
                String password = "12345678";
                connection = DriverManager.getConnection(url, user, password);
            }
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String url = "jdbc:postgresql://localhost/poomaven_db";
            String user = "postgres";
            String password = "12345678";
            Connection connection = null;

            try {
                connection = DriverManager.getConnection(url, user, password);
                new ConductorambulanciaUI(connection);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });
    }
}
