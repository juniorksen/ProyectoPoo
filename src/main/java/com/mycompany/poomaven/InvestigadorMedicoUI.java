package com.mycompany.poomaven;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class InvestigadorMedicoUI extends JFrame {
    private static InvestigadorMedicoUI instance; // Instancia única
    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;

    private InvestigadorMedicoUI(List<InvestigadorMedico> investigadores) { // Constructor privado
        setTitle("Lista de Investigadores Médicos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel de búsqueda
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel searchLabel = new JLabel("Buscar por ID de proyectos: ");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        searchField = new JTextField(10);
        JButton searchButton = new JButton("Buscar");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarDatos(investigadores);
            }
        });
        
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // Crear la tabla y el modelo
        model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Campo de Investigación");
        model.addColumn("ID de proyectos");
        table = new JTable(model);
        table.setFillsViewportHeight(true);

        // Estilizar tabla
        table.setRowHeight(25);
        table.setFont(new Font("SansSerif", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(60, 80, 120));
        table.getTableHeader().setForeground(Color.white);
        
        cargarDatos(investigadores);
        
        // Añadir la tabla al panel de desplazamiento
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    // Método estático para obtener la instancia única
    public static InvestigadorMedicoUI getInstance(List<InvestigadorMedico> investigadores) {
        if (instance == null) {
            instance = new InvestigadorMedicoUI(investigadores);
        }
        return instance;
    }

    private void cargarDatos(List<InvestigadorMedico> investigadores) {
        model.setRowCount(0); // Limpiar datos previos
        for (InvestigadorMedico investigador : investigadores) {
            model.addRow(new Object[]{
                investigador.getNombre(),
                investigador.getCampoInvestigacion(),
                investigador.getProyectosActivos()
            });
        }
    }

    private void filtrarDatos(List<InvestigadorMedico> investigadores) {
        String filtro = searchField.getText().trim();
        if (!filtro.isEmpty()) {
            try {
                int proyectosActivos = Integer.parseInt(filtro);
                List<InvestigadorMedico> filtrados = investigadores.stream()
                        .filter(investigador -> investigador.getProyectosActivos() == proyectosActivos)
                        .collect(Collectors.toList());
                cargarDatos(filtrados);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor ingresa un número válido para los proyectos activos.",
                        "Error de entrada", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            cargarDatos(investigadores); // Mostrar todos si el campo de búsqueda está vacío
        }
    }
}
