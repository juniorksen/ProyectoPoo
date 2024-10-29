package com.mycompany.poomaven;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class GestorMantenimientoUI extends JFrame {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final GestorMantenimientoDAO gestorMantenimientoDAO;

    public GestorMantenimientoUI(Connection connection) {
        // Configuración de la interfaz gráfica
        setTitle("Gestor de Mantenimiento");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el DAO para interactuar con la base de datos
        gestorMantenimientoDAO = new GestorMantenimientoDAOImpl(connection);

        // Crear la tabla y su modelo sin las columnas de Actividad y Detalle
        tableModel = new DefaultTableModel(new String[]{
            "Nombre", 
            "Área de Responsabilidad", 
            "Tipo de Equipo", 
            "Proyectos Activos", 
            "Nivel Educativo", 
            "Especialidad Docente"
        }, 0);
        table = new JTable(tableModel);

        // Añadir la tabla a la interfaz
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Cargar todos los datos al iniciar la interfaz
        cargarTodosLosDatos();
    }

    // Método para cargar todos los datos
    private void cargarTodosLosDatos() {
        tableModel.setRowCount(0);
        List<GestorMantenimientoPlanta> gestores = gestorMantenimientoDAO.obtenerTodosLosGestores();
        for (GestorMantenimientoPlanta gestor : gestores) {
            tableModel.addRow(new Object[]{
                gestor.getNombre(),
                gestor.getAreaResponsabilidad(),
                gestor.getTipoDeEquipo(),
                gestor.getProyectosActivos(),
                gestor.getNivelEducativo(),
                gestor.getEspecialidadDocente()
            });
        }
    }

    // Método para obtener el gestor seleccionado
    private GestorMantenimientoPlanta obtenerGestorSeleccionado(int selectedRow) {
        // Obtener el objeto GestorMantenimientoPlanta según la fila seleccionada en la tabla.
        String nombre = (String) tableModel.getValueAt(selectedRow, 0);
        return gestorMantenimientoDAO.obtenerGestorPorNombre(nombre); // Ajusta según tu implementación
    }
}
