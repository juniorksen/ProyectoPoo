package com.mycompany.poomaven;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; // Importación necesaria
import java.util.List;

public class InstructorMedicoUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;
    private JButton searchButton;

    public InstructorMedicoUI(List<InstructorMedico> instructores) {
        setTitle("Lista de Instructores Médicos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear la tabla y el modelo
        model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Nivel Educativo");
        model.addColumn("Especialidad Docente");
        model.addColumn("Cedula");
        model.addColumn("Detalle"); // Nueva columna para el detalle
        table = new JTable(model);
        table.setFillsViewportHeight(true);

        // Estilizar tabla
        table.setRowHeight(40); // Establece una altura mínima para las filas
        table.setFont(new Font("SansSerif", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(60, 80, 120));
        table.getTableHeader().setForeground(Color.white);

        // Establecer un renderizador de celdas que ajuste el texto
        table.setDefaultRenderer(Object.class, new WordWrapCellRenderer());

        // Crear campo de búsqueda y botón
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());

        searchField = new JTextField(10);
        searchButton = new JButton("Buscar");

        // Añadir acción al botón de búsqueda
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorProyectosActivos(instructores);
            }
        });

        searchPanel.add(new JLabel("Proyectos Activos:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        cargarDatos(instructores);

        // Añadir componentes al panel principal
        add(searchPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void cargarDatos(List<InstructorMedico> instructores) {
        model.setRowCount(0); // Limpiar datos previos
        for (InstructorMedico instructor : instructores) {
            model.addRow(new Object[]{
                instructor.getNombre(),
                instructor.getNivelEducativo(),
                instructor.getEspecialidadDocente(),
                instructor.getProyectosActivos(),
                instructor.getDetalle() // Agregar detalle a la tabla
            });
        }
        ajustarAlturaFilas();
    }

    private void ajustarAlturaFilas() {
        for (int i = 0; i < table.getRowCount(); i++) {
            int rowHeight = table.getRowHeight();
            for (int j = 0; j < table.getColumnCount(); j++) {
                TableCellRenderer renderer = table.getCellRenderer(i, j);
                Component comp = table.prepareRenderer(renderer, i, j);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }
            table.setRowHeight(i, rowHeight);
        }
    }

    private void buscarPorProyectosActivos(List<InstructorMedico> instructores) {
        String input = searchField.getText();
        if (input.isEmpty()) {
            // Si no hay entrada, mostrar todos los instructores
            cargarDatos(instructores);
            return;
        }

        try {
            int proyectosActivos = Integer.parseInt(input);
            List<InstructorMedico> resultados = new ArrayList<>();

            for (InstructorMedico instructor : instructores) {
                if (instructor.getProyectosActivos() == proyectosActivos) {
                    resultados.add(instructor);
                }
            }

            cargarDatos(resultados);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un número válido para proyectos activos.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {
        public WordWrapCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value != null ? value.toString() : "");
            setFont(table.getFont()); // Asegúrate de que el texto tenga el mismo tipo de letra que la tabla
            setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());

            return this;
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(100, 50); // Establece un tamaño fijo
        }
    }
}
