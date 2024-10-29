package com.mycompany.poomaven;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonalAseoDAO {
    private Connection connection;

    public PersonalAseoDAO(Connection connection) {
        this.connection = connection;
    }

    public List<PersonalAseo> obtenerDatosDePersonalAseo() {
        List<PersonalAseo> personalList = new ArrayList<>();
        String selectQuery = "SELECT id, hora, tiempotrabajado, cedula, nombre, area, correo FROM personal_aseo"; // Asegúrate de incluir 'id'

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id"); // Obtener id de la consulta
                String hora = resultSet.getString("hora");
                String tiempotrabajado = resultSet.getString("tiempotrabajado");
                String cedula = resultSet.getString("cedula");
                String nombre = resultSet.getString("nombre");
                String area = resultSet.getString("area");
                String correo = resultSet.getString("correo");

                // Aquí se crea la instancia de PersonalAseo con el nuevo constructor que incluye id
                PersonalAseo personal = new PersonalAseo(id, hora, tiempotrabajado, cedula, nombre, area, correo); 
                personalList.add(personal);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener datos del personal de aseo.");
            e.printStackTrace();
        }
        return personalList;
    }

    // Otros métodos de acceso a la base de datos para PersonalAseo podrían ir aquí, como insertar, actualizar, eliminar, etc.
}
