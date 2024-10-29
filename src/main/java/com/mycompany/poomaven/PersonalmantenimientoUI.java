package com.mycompany.poomaven;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class PersonalmantenimientoUI extends JFrame {
    private Connection connection;
    private JTable table;
    private DefaultTableModel model;
    private JTextField filterField;

    public PersonalmantenimientoUI(Connection connection) {
        this.connection = connection;
        setTitle("Gestión de Personal de Mantenimiento");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Encabezado estilizado
        JLabel headerLabel = new JLabel("Personal de Mantenimiento", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(new Color(60, 63, 65));
        headerLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        add(headerLabel, BorderLayout.NORTH);

        // Configuración de la tabla
        model = new DefaultTableModel(new String[]{
                "Turno Semana", "Tiempo Trabajado", "Hora Inicio", "Hora Fin", 
                "Cédula", "Nombre", "Apellido", "Correo"}, 0);
        
        table = new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setGridColor(new Color(210, 210, 210));

        // Estilizar celdas de encabezado
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(70, 130, 180));
        headerRenderer.setForeground(Color.WHITE);
        for (int i = 0; i < table.getModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        // Estilizar las celdas de la tabla
        table.setSelectionBackground(new Color(135, 206, 250));
        table.setSelectionForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel de filtro con color de fondo suave
        JPanel filterPanel = new JPanel();
        filterPanel.setBackground(new Color(240, 248, 255));
        filterPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        filterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        filterField = new JTextField(15);
        JButton filterButton = new JButton("Filtrar");
        filterButton.setBackground(new Color(70, 130, 180));
        filterButton.setForeground(Color.WHITE);
        filterButton.setFocusPainted(false);
        filterButton.addActionListener(this::filterData);
        
        filterPanel.add(new JLabel("Filtrar por Cédula:"));
        filterPanel.add(filterField);
        filterPanel.add(filterButton);
        add(filterPanel, BorderLayout.SOUTH);

        // Cargar los datos inicialmente
        loadData();

        setVisible(true);
    }

    private void loadData() {
        if (!checkConnection()) {
            return;
        }

        model.setRowCount(0); // Limpiar datos previos
        String query = "SELECT * FROM personalmantenimiento";

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
            JOptionPane.showMessageDialog(this, "Error al cargar los datos del personal de mantenimiento.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void filterData(ActionEvent e) {
        if (!checkConnection()) {
            return;
        }

        String cedulaFiltrada = filterField.getText().trim();
        model.setRowCount(0); // Limpiar datos previos
        String query = "SELECT * FROM personalmantenimiento WHERE cedula LIKE ?";

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
            JOptionPane.showMessageDialog(this, "Error al cargar los datos filtrados del personal de mantenimiento.", "Error", JOptionPane.ERROR_MESSAGE);
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
                new PersonalmantenimientoUI(connection);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });
    }
}
