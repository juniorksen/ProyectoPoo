package com.mycompany.poomaven;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JefeComunicacionesUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;
    private JButton searchButton;

    public JefeComunicacionesUI(List<JefeComunicaciones> jefes) {
        setTitle("Lista de Jefes de Comunicaciones");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear la tabla y el modelo con todas las columnas necesarias
        model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Nivel Educativo");
        model.addColumn("Especialidad");
        model.addColumn("Cedula");
        model.addColumn("Canal de Comunicación");
        model.addColumn("Tipo de Mensaje");

        table = new JTable(model);
        table.setFillsViewportHeight(true);

        // Estilizar tabla
        table.setRowHeight(40);
        table.setFont(new Font("SansSerif", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(60, 80, 120));
        table.getTableHeader().setForeground(Color.white);

        // Configurar renderizador de celdas para ajustar texto
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
                buscarPorProyectosActivos(jefes);
            }
        });

        searchPanel.add(new JLabel("Proyectos Activos:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        cargarDatos(jefes);

        // Añadir componentes al panel principal
        add(searchPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void cargarDatos(List<JefeComunicaciones> jefes) {
        model.setRowCount(0); // Limpiar datos previos
        for (JefeComunicaciones jefe : jefes) {
            model.addRow(new Object[]{
                jefe.getNombre(),
                jefe.getNivelEducativo(),
                jefe.getEspecialidad(),
                jefe.getProyectosActivos(),
                jefe.getCanalDeComunicacion(),
                jefe.getTipoDeMensaje(),
                jefe.getDetalleActividad() // Detalle de la actividad
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

    private void buscarPorProyectosActivos(List<JefeComunicaciones> jefes) {
        String input = searchField.getText();
        if (input.isEmpty()) {
            // Si no hay entrada, mostrar todos los jefes
            cargarDatos(jefes);
            return;
        }

        try {
            int proyectosActivos = Integer.parseInt(input);
            List<JefeComunicaciones> resultados = new ArrayList<>();

            for (JefeComunicaciones jefe : jefes) {
                if (jefe.getProyectosActivos() == proyectosActivos) {
                    resultados.add(jefe);
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
            setFont(table.getFont());
            setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());

            return this;
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(100, 50);
        }
    }
}
