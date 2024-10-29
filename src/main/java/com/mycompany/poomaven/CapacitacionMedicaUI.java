package com.mycompany.poomaven;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CapacitacionMedicaUI extends JFrame {
    private final JTextField filterField;
    private final JButton filterButton;
    private final JTable table;
    private final DefaultTableModel tableModel;
    private static CapacitacionMedicaUI instance;

    // Cambiar el constructor para aceptar una lista de CapacitacionMedico
    private CapacitacionMedicaUI(List<CapacitacionMedico> capacitaciones) {
        // Configurar la interfaz
        setTitle("Capacitación Médica");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear campo de texto para filtro y botón de filtro
        filterField = new JTextField(10);
        filterButton = new JButton("Filtrar por Proyectos Activos");

        // Crear la tabla y su modelo
        tableModel = new DefaultTableModel(new String[]{"Nombre", "Campo Investigación", "Nivel Educativo", "Especialidad Docente", "Proyectos Activos"}, 0);
        table = new JTable(tableModel);

        // Añadir componentes al panel superior
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Proyectos Activos:"));
        topPanel.add(filterField);
        topPanel.add(filterButton);

        // Añadir panel y tabla a la interfaz
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Acción de filtrado por el campo "Proyectos Activos" al hacer clic en "Filtrar"
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filterText = filterField.getText();
                if (filterText.isEmpty()) {
                    cargarDatos(capacitaciones); // Mostrar todos los datos si el filtro está vacío
                } else {
                    try {
                        int proyectosActivos = Integer.parseInt(filterText);
                        filtrarPorProyectosActivos(capacitaciones, proyectosActivos);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(CapacitacionMedicaUI.this, "Ingrese un número válido para proyectos activos.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Cargar todos los datos al iniciar la interfaz
        cargarDatos(capacitaciones);
    }

    // Singleton para la interfaz
    public static CapacitacionMedicaUI getInstance(List<CapacitacionMedico> capacitaciones) {
        if (instance == null) {
            instance = new CapacitacionMedicaUI(capacitaciones);
        }
        return instance;
    }

    // Método para cargar todos los datos
    private void cargarDatos(List<CapacitacionMedico> capacitaciones) {
        tableModel.setRowCount(0);  // Limpiar la tabla
        for (CapacitacionMedico capacitacion : capacitaciones) {
            tableModel.addRow(new Object[]{
                capacitacion.getNombre(),
                capacitacion.getCampoInvestigacion(),
                capacitacion.getNivelEducativo(),
                capacitacion.getEspecialidadDocente(),
                capacitacion.getProyectosActivos()
            });
        }
    }

    // Método para filtrar los datos por el campo "Proyectos Activos"
    private void filtrarPorProyectosActivos(List<CapacitacionMedico> capacitaciones, int proyectosActivos) {
        List<CapacitacionMedico> resultadosFiltrados = capacitaciones.stream()
                .filter(c -> c.getProyectosActivos() == proyectosActivos)
                .toList();

        if (resultadosFiltrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron resultados para " + proyectosActivos + " proyectos activos.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            cargarDatos(capacitaciones); // Mostrar todos los datos si no hay resultados
        } else {
            cargarDatos(resultadosFiltrados); // Mostrar solo los datos filtrados
        }
    }
}
