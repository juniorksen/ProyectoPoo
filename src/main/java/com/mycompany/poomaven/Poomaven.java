package com.mycompany.poomaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList; // Importa ArrayList
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Date;
import java.util.List; // Importa List


public class Poomaven {
    

     private static Connection connection;
    
    
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost/poomaven_db";
        String user = "postgres";
        String password = "12345678";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexión exitosa a la base de datos!");
            Statement statement = connection.createStatement();
            
            //Agregación
        
               // Crear la tabla 'farmacia' si no existe
            String createFarmaciaTableQuery = "CREATE TABLE IF NOT EXISTS farmacia ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "direccion VARCHAR(100) NOT NULL"
                    + ")";
            statement.execute(createFarmaciaTableQuery);
            System.out.println("Tabla 'farmacia' creada exitosamente!");

            // Crear la tabla 'medicamentos' si no existe
            String createMedicamentosTableQuery = "CREATE TABLE IF NOT EXISTS medicamentos ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "precio DECIMAL(5, 2) NOT NULL, "
                    + "farmacia_id INTEGER REFERENCES farmacia(id)"
                    + ")";
            statement.execute(createMedicamentosTableQuery);
            System.out.println("Tabla 'medicamentos' creada exitosamente!");
            
            
            
            // Crear la tabla 'enfermeras' si no existe
            String createEnfermerasTableQuery = "CREATE TABLE IF NOT EXISTS enfermeras ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "apellido VARCHAR(100) NOT NULL"
                    + ")";
            PreparedStatement pstmtEnfermeras = connection.prepareStatement(createEnfermerasTableQuery);
            pstmtEnfermeras.execute();
            System.out.println("Tabla 'enfermeras' creada exitosamente!");

            // Crear la tabla 'turnos' si no existe
            String createTurnosTableQuery = "CREATE TABLE IF NOT EXISTS turnos ("
                    + "id SERIAL PRIMARY KEY, "
                    + "hora_inicio VARCHAR(100) NOT NULL, "
                    + "hora_fin VARCHAR(100) NOT NULL, "
                    + "enfermera_id INT REFERENCES enfermeras(id) ON DELETE CASCADE"
                    + ")";
            PreparedStatement pstmtTurnos = connection.prepareStatement(createTurnosTableQuery);
            pstmtTurnos.execute();
            System.out.println("Tabla 'turnos' creada exitosamente!");
            
            // Crear la tabla 'administrativos' si no existe
            String createAdministrativosTableQuery = "CREATE TABLE IF NOT EXISTS administrativos ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "apellido VARCHAR(100) NOT NULL"
                    + ")";
            PreparedStatement pstmtAdministrativos = connection.prepareStatement(createAdministrativosTableQuery);
            pstmtAdministrativos.execute();
            System.out.println("Tabla 'administrativos' creada exitosamente!");

            // Crear la tabla 'departamentos' si no existe
            String createDepartamentosTableQuery = "CREATE TABLE IF NOT EXISTS departamentos ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "administrativo_id INT REFERENCES administrativos(id) ON DELETE CASCADE"
                    + ")";
            PreparedStatement pstmtDepartamentos = connection.prepareStatement(createDepartamentosTableQuery);
            pstmtDepartamentos.execute();
            System.out.println("Tabla 'departamentos' creada exitosamente!");
            
            
            
            // Crear la tabla 'laboratorios' si no existe
            String createLaboratoriosTableQuery = "CREATE TABLE IF NOT EXISTS laboratorios ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "ubicacion VARCHAR(100) NOT NULL"
                    + ")";
            PreparedStatement pstmtLaboratorios = connection.prepareStatement(createLaboratoriosTableQuery);
            pstmtLaboratorios.execute();
            System.out.println("Tabla 'laboratorios' creada exitosamente!");

            // Crear la tabla 'examenes_laboratorio' si no existe
            String createExamenesLaboratorioTableQuery = "CREATE TABLE IF NOT EXISTS examenes_laboratorio ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "costo DECIMAL(10, 2) NOT NULL, "
                    + "laboratorio_id INT REFERENCES laboratorios(id) ON DELETE CASCADE"
                    + ")";
            PreparedStatement pstmtExamenesLaboratorio = connection.prepareStatement(createExamenesLaboratorioTableQuery);
            pstmtExamenesLaboratorio.execute();
            System.out.println("Tabla 'examenes_laboratorio' creada exitosamente!");
            
            
            
                    // Crear la tabla 'quirofanos' si no existe
        String createQuirofanosTableQuery = "CREATE TABLE IF NOT EXISTS quirofanos ("
                + "id SERIAL PRIMARY KEY, "
                + "nombre VARCHAR(100) NOT NULL, "
                + "ubicacion VARCHAR(100) NOT NULL"
                + ")";
        PreparedStatement pstmtQuirofanos = connection.prepareStatement(createQuirofanosTableQuery);
        pstmtQuirofanos.execute();
        System.out.println("Tabla 'quirofanos' creada exitosamente!");

        // Crear la tabla 'cirugias' si no existe
        String createCirugiasTableQuery = "CREATE TABLE IF NOT EXISTS cirugias ("
                + "id SERIAL PRIMARY KEY, "
                + "tipo VARCHAR(100) NOT NULL, "
                + "fecha VARCHAR(100) NOT NULL, "
                + "quirofano_id INT REFERENCES quirofanos(id) ON DELETE CASCADE"
                + ")";
        PreparedStatement pstmtCirugias = connection.prepareStatement(createCirugiasTableQuery);
        pstmtCirugias.execute();
        System.out.println("Tabla 'cirugias' creada exitosamente!");
        
        
        // Crear la tabla 'salas_espera' si no existe
            String createSalasEsperaTableQuery = "CREATE TABLE IF NOT EXISTS salas_espera ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "capacidad INT NOT NULL"
                    + ")";
            PreparedStatement pstmtSalasEspera = connection.prepareStatement(createSalasEsperaTableQuery);
            pstmtSalasEspera.execute();
            System.out.println("Tabla 'salas_espera' creada exitosamente!");

            // Crear la tabla 'citas_medicas' si no existe
            String createCitasMedicasTableQuery = "CREATE TABLE IF NOT EXISTS citas_medicas ("
                    + "id SERIAL PRIMARY KEY, "
                    + "fecha VARCHAR(100) NOT NULL, "
                    + "hora VARCHAR(100) NOT NULL, "
                    + "sala_espera_id INT REFERENCES salas_espera(id) ON DELETE CASCADE"
                    + ")";
            PreparedStatement pstmtCitasMedicas = connection.prepareStatement(createCitasMedicasTableQuery);
            pstmtCitasMedicas.execute();
            System.out.println("Tabla 'citas_medicas' creada exitosamente!");

        
         // Crear la tabla 'carros_ambulancia'
            String createCarrosAmbulanciaTableQuery = "CREATE TABLE IF NOT EXISTS carros_ambulancia ("
                    + "id SERIAL PRIMARY KEY, "
                    + "placa VARCHAR(100) NOT NULL, "
                    + "modelo VARCHAR(100) NOT NULL)";
            PreparedStatement pstmtCarrosAmbulancia = connection.prepareStatement(createCarrosAmbulanciaTableQuery);
            pstmtCarrosAmbulancia.execute();
            System.out.println("Tabla 'carros_ambulancia' creada exitosamente!");

            // Crear la tabla 'servicios_emergencia'
            String createServiciosEmergenciaTableQuery = "CREATE TABLE IF NOT EXISTS servicios_emergencia ("
                    + "id SERIAL PRIMARY KEY, "
                    + "descripcion VARCHAR(255) NOT NULL, "
                    + "fecha VARCHAR(100) NOT NULL, "
                    + "carro_ambulancia_id INT REFERENCES carros_ambulancia(id) ON DELETE CASCADE)";
            PreparedStatement pstmtServiciosEmergencia = connection.prepareStatement(createServiciosEmergenciaTableQuery);
            pstmtServiciosEmergencia.execute();
            System.out.println("Tabla 'servicios_emergencia' creada exitosamente!");

            
            // Crear la tabla 'areas_radiologia' si no existe
            String createAreasRadiologiaTableQuery = "CREATE TABLE IF NOT EXISTS areas_radiologia ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL"
                    + ")";
            PreparedStatement pstmtAreasRadiologia = connection.prepareStatement(createAreasRadiologiaTableQuery);
            pstmtAreasRadiologia.execute();
            System.out.println("Tabla 'areas_radiologia' creada exitosamente!");

            // Crear la tabla 'estudios_radiologicos' si no existe
            String createEstudiosRadiologicosTableQuery = "CREATE TABLE IF NOT EXISTS estudios_radiologicos ("
                    + "id SERIAL PRIMARY KEY, "
                    + "tipo_estudio VARCHAR(100) NOT NULL, "
                    + "fecha VARCHAR(100) NOT NULL, "
                    + "area_radiologia_id INT REFERENCES areas_radiologia(id) ON DELETE CASCADE"
                    + ")";
            PreparedStatement pstmtEstudiosRadiologicos = connection.prepareStatement(createEstudiosRadiologicosTableQuery);
            pstmtEstudiosRadiologicos.execute();
            System.out.println("Tabla 'estudios_radiologicos' creada exitosamente!");

            
            ////composicion
            
            
            // Crear la tabla 'unidad_cuidados_intensivos' si no existe
            String createUciTableQuery = "CREATE TABLE IF NOT EXISTS unidad_cuidados_intensivos ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "ubicacion VARCHAR(100) NOT NULL"
                    + ")";
            PreparedStatement pstmtUci = connection.prepareStatement(createUciTableQuery);
            pstmtUci.execute();
            System.out.println("Tabla 'unidad_cuidados_intensivos' creada exitosamente!");

            // Crear la tabla 'equipo_medico' si no existe
            String createEquipoMedicoTableQuery = "CREATE TABLE IF NOT EXISTS equipo_medico ("
                    + "id SERIAL PRIMARY KEY, "
                    + "tipo VARCHAR(100) NOT NULL, "
                    + "fabricante VARCHAR(100) NOT NULL, "
                    + "uci_id INT REFERENCES unidad_cuidados_intensivos(id) ON DELETE CASCADE"
                    + ")";
            PreparedStatement pstmtEquipoMedico = connection.prepareStatement(createEquipoMedicoTableQuery);
            pstmtEquipoMedico.execute();
            System.out.println("Tabla 'equipo_medico' creada exitosamente!");
            

            // Crear la tabla 'historia_clinica' si no existe
            String createHistoriaClinicaTableQuery = "CREATE TABLE IF NOT EXISTS historia_clinica ("
                    + "id SERIAL PRIMARY KEY, "
                    + "paciente VARCHAR(100) NOT NULL, "
                    + "fecha_apertura DATE NOT NULL"
                    + ")";
            PreparedStatement pstmtHistoria = connection.prepareStatement(createHistoriaClinicaTableQuery);
            pstmtHistoria.execute();
            System.out.println("Tabla 'historia_clinica' creada exitosamente!");

            // Crear la tabla 'diagnostico' si no existe
            String createDiagnosticoTableQuery = "CREATE TABLE IF NOT EXISTS diagnostico ("
                    + "id SERIAL PRIMARY KEY, "
                    + "descripcion TEXT NOT NULL, "
                    + "medico VARCHAR(100) NOT NULL, "
                    + "historia_clinica_id INT REFERENCES historia_clinica(id) ON DELETE CASCADE"
                    + ")";
            PreparedStatement pstmtDiagnostico = connection.prepareStatement(createDiagnosticoTableQuery);
            pstmtDiagnostico.execute();
            System.out.println("Tabla 'diagnostico' creada exitosamente!");
            
            
            
            // Crear la tabla 'sala_rehabilitacion' si no existe
        String createSalaRehabilitacionTableQuery = "CREATE TABLE IF NOT EXISTS sala_rehabilitacion ("
                + "id SERIAL PRIMARY KEY, "
                + "nombre VARCHAR(100) NOT NULL, "
                + "ubicacion VARCHAR(100) NOT NULL"
                + ")";
        PreparedStatement pstmtSala = connection.prepareStatement(createSalaRehabilitacionTableQuery);
        pstmtSala.execute();
        System.out.println("Tabla 'sala_rehabilitacion' creada exitosamente!");

        // Crear la tabla 'programa_rehabilitacion' si no existe
        String createProgramaRehabilitacionTableQuery = "CREATE TABLE IF NOT EXISTS programa_rehabilitacion ("
                + "id SERIAL PRIMARY KEY, "
                + "nombre VARCHAR(100) NOT NULL, "
                + "descripcion TEXT NOT NULL, "
                + "sala_rehabilitacion_id INT REFERENCES sala_rehabilitacion(id) ON DELETE CASCADE"
                + ")";
        PreparedStatement pstmtPrograma = connection.prepareStatement(createProgramaRehabilitacionTableQuery);
        pstmtPrograma.execute();
        System.out.println("Tabla 'programa_rehabilitacion' creada exitosamente!");
        

            // Crear la tabla 'unidad_de_cuidado' si no existe
            String createUnidadDeCuidadoTableQuery = "CREATE TABLE IF NOT EXISTS unidad_de_cuidado ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "direccion VARCHAR(255) NOT NULL"
                    + ")";
            PreparedStatement pstmtUnidadDeCuidado = connection.prepareStatement(createUnidadDeCuidadoTableQuery);
            pstmtUnidadDeCuidado.execute();
            System.out.println("Tabla 'unidad_de_cuidado' creada exitosamente!");

            // Crear la tabla 'servicio' si no existe
            String createServicioTableQuery = "CREATE TABLE IF NOT EXISTS servicio ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "costo DOUBLE PRECISION NOT NULL, "
                    + "personal_encargado VARCHAR(100) NOT NULL, "
                    + "duracion INT NOT NULL, "
                    + "unidad_cuidado_id INT REFERENCES unidad_de_cuidado(id) ON DELETE CASCADE"
                    + ")";
            PreparedStatement pstmtServicio = connection.prepareStatement(createServicioTableQuery);
            pstmtServicio.execute();
            System.out.println("Tabla 'servicio' creada exitosamente!");

            // Crear la tabla 'inventario' si no existe
            String createInventarioTableQuery = "CREATE TABLE IF NOT EXISTS inventario ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL"
                    + ")";
            PreparedStatement pstmtInventario = connection.prepareStatement(createInventarioTableQuery);
            pstmtInventario.execute();
            System.out.println("Tabla 'inventario' creada exitosamente!");

            // Crear la tabla 'suministro' si no existe
            String createSuministroTableQuery = "CREATE TABLE IF NOT EXISTS suministro ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "cantidad DOUBLE PRECISION NOT NULL, "
                    + "unidad_medida VARCHAR(50) NOT NULL, "
                    + "inventario_id INT REFERENCES inventario(id) ON DELETE CASCADE"
                    + ")";
            PreparedStatement pstmtSuministro = connection.prepareStatement(createSuministroTableQuery);
            pstmtSuministro.execute();
            System.out.println("Tabla 'suministro' creada exitosamente!");
            
            // Crear la tabla 'sala_recepcion' si no existe
            String createSalaRecepcionTableQuery = "CREATE TABLE IF NOT EXISTS sala_recepcion ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL"
                    + ")";
            PreparedStatement pstmtSalaRecepcion = connection.prepareStatement(createSalaRecepcionTableQuery);
            pstmtSalaRecepcion.execute();
            System.out.println("Tabla 'sala_recepcion' creada exitosamente!");

            // Crear la tabla 'revista' si no existe
            String createRevistaTableQuery = "CREATE TABLE IF NOT EXISTS revista ("
                    + "id SERIAL PRIMARY KEY, "
                    + "titulo VARCHAR(100) NOT NULL, "
                    + "fecha_publicacion VARCHAR(20) NOT NULL, "
                    + "sala_recepcion_id INT REFERENCES sala_recepcion(id) ON DELETE CASCADE"
                    + ")";
            PreparedStatement pstmtRevista = connection.prepareStatement(createRevistaTableQuery);
            pstmtRevista.execute();
            System.out.println("Tabla 'revista' creada exitosamente!");
            
            // Crear la tabla 'centro_atencion_urgente' si no existe
                String createCentroAtencionTableQuery = "CREATE TABLE IF NOT EXISTS centro_atencion_urgente ("
                        + "id SERIAL PRIMARY KEY, "
                        + "nombre VARCHAR(100) NOT NULL"
                        + ")";
                PreparedStatement pstmtCentroAtencion = connection.prepareStatement(createCentroAtencionTableQuery);
                pstmtCentroAtencion.execute();
                System.out.println("Tabla 'centro_atencion_urgente' creada exitosamente!");

                // Crear la tabla 'protocolo_atencion' si no existe
                String createProtocoloAtencionTableQuery = "CREATE TABLE IF NOT EXISTS protocolo_atencion ("
                        + "id SERIAL PRIMARY KEY, "
                        + "nombre VARCHAR(100) NOT NULL, "
                        + "descripcion TEXT NOT NULL, "
                        + "centro_atencion_id INT REFERENCES centro_atencion_urgente(id) ON DELETE CASCADE"
                        + ")";
                PreparedStatement pstmtProtocoloAtencion = connection.prepareStatement(createProtocoloAtencionTableQuery);
                pstmtProtocoloAtencion.execute();
                System.out.println("Tabla 'protocolo_atencion' creada exitosamente!");

                
                // Crear la tabla 'area_observacion' si no existe
                String createAreaObservacionTableQuery = "CREATE TABLE IF NOT EXISTS area_observacion ("
                        + "id SERIAL PRIMARY KEY, "
                        + "nombre VARCHAR(100) NOT NULL"
                        + ")";
                PreparedStatement pstmtAreaObservacion = connection.prepareStatement(createAreaObservacionTableQuery);
                pstmtAreaObservacion.execute();
                System.out.println("Tabla 'area_observacion' creada exitosamente!");

                // Crear la tabla 'equipo_monitoreo' si no existe
                String createEquipoMonitoreoTableQuery = "CREATE TABLE IF NOT EXISTS equipo_monitoreo ("
                        + "id SERIAL PRIMARY KEY, "
                        + "nombre VARCHAR(100) NOT NULL, "
                        + "tipo VARCHAR(100) NOT NULL, "
                        + "area_observacion_id INT REFERENCES area_observacion(id) ON DELETE CASCADE"
                        + ")";
                PreparedStatement pstmtEquipoMonitoreo = connection.prepareStatement(createEquipoMonitoreoTableQuery);
                pstmtEquipoMonitoreo.execute();
                System.out.println("Tabla 'equipo_monitoreo' creada exitosamente!");

                //polimorfismo        

                // Crear la tabla 'examenes' si no existe
               String createExamenesTableQuery = "CREATE TABLE IF NOT EXISTS examenes ("
                       + "id SERIAL PRIMARY KEY, "
                       + "nombre VARCHAR(100) NOT NULL, "
                       + "precio DOUBLE PRECISION NOT NULL"
                       + ")";
               statement.execute(createExamenesTableQuery);
               System.out.println("Tabla 'examenes' creada exitosamente!");
               
               // Crear la tabla 'tratamientos' si no existe
            String createTratamientosTableQuery = "CREATE TABLE IF NOT EXISTS tratamientos ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "duracion_dias INT NOT NULL"
                    + ")";
            statement.execute(createTratamientosTableQuery);
            System.out.println("Tabla 'tratamientos' creada exitosamente!");
            
            // Crear la tabla 'cama' si no existe
            String createCamaTableQuery = "CREATE TABLE IF NOT EXISTS cama ("
                    + "id SERIAL PRIMARY KEY, "
                    + "ubicacion VARCHAR(100) NOT NULL, "
                    + "ocupada BOOLEAN NOT NULL"
                    + ")";
            statement.execute(createCamaTableQuery);
            System.out.println("Tabla 'cama' creada exitosamente!");
           
            
                // Crear la tabla 'procedimientosssssssss' si no existe
            String createProcedimientoTableQuery = "CREATE TABLE IF NOT EXISTS procedimiento_medico ("
                    + "id SERIAL PRIMARY KEY, "
                    + "tipo VARCHAR(100) NOT NULL, "
                    + "duracion INT NOT NULL"
                    + ")";
            statement.execute(createProcedimientoTableQuery);
            System.out.println("Tabla 'procedimiento_medico' creada exitosamente!");

                // Crear la tabla 'RecetaMedica' si no existe
            String createRecetaMedicaTableQuery = "CREATE TABLE IF NOT EXISTS receta_medica ("
                    + "id SERIAL PRIMARY KEY, "
                    + "tipo_receta VARCHAR(100) NOT NULL, "
                    + "medicamento VARCHAR(100) NOT NULL"
                    + ")";
            statement.execute(createRecetaMedicaTableQuery);
            System.out.println("Tabla 'receta_medica' creada exitosamente!");



            // Crear la tabla 'Guardiaseguridad' si no existe
          String createGuardiaseguridadTableQuery = "CREATE TABLE IF NOT EXISTS guardiaseguridad ("
                    + "id SERIAL PRIMARY KEY, "
                    + "horainicio VARCHAR(100), "
                    + "horafin VARCHAR(100), "
                    + "cedula VARCHAR(15) NOT NULL, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "apellido VARCHAR(100), "
                    + "correo VARCHAR(100), "
                    + "turnossemana VARCHAR(100), "  // Añadir el campo turnossemana
                    + "tiempotrabajado VARCHAR(100)"  // Añadir el campo tiempotrabajado
                    + ")";
            statement.execute(createGuardiaseguridadTableQuery);
            System.out.println("Tabla 'guardiaseguridad' creada exitosamente!");
            
              // Crear la tabla 'Conductorambulancia' si no existe
             String createConductorambulanciaTableQuery = "CREATE TABLE IF NOT EXISTS conductorambulancia ("  // Cambia 'guardiaseguridad' por 'conductorambulancia'
                     + "id SERIAL PRIMARY KEY, "
                     + "horainicio VARCHAR(100), "
                     + "horafin VARCHAR(100), "
                     + "cedula VARCHAR(15) NOT NULL, "
                     + "nombre VARCHAR(100) NOT NULL, "
                     + "apellido VARCHAR(100), "
                     + "correo VARCHAR(100), "
                     + "turnossemana VARCHAR(100), "  // Añadir el campo turnossemana
                     + "tiempotrabajado VARCHAR(100)"  // Añadir el campo tiempotrabajado
                     + ")";
             statement.execute(createConductorambulanciaTableQuery);
             System.out.println("Tabla 'Conductor de ambulancia' creada exitosamente!");
             
               // Crear la tabla 'Personalmantenimiento' si no existe
             String createPersonalmantenimientoTableQuery = "CREATE TABLE IF NOT EXISTS personalmantenimiento (" 
                     + "id SERIAL PRIMARY KEY, "
                     + "horainicio VARCHAR(100), "
                     + "horafin VARCHAR(100), "
                     + "cedula VARCHAR(15) NOT NULL, "
                     + "nombre VARCHAR(100) NOT NULL, "
                     + "apellido VARCHAR(100), "
                     + "correo VARCHAR(100), "
                     + "turnossemana VARCHAR(100), "  // Añadir el campo turnossemana
                     + "tiempotrabajado VARCHAR(100)"  // Añadir el campo tiempotrabajado
                     + ")";
             statement.execute(createPersonalmantenimientoTableQuery);
             System.out.println("Tabla Personal de mantenimiento' creada exitosamente!");
             
             
               // Crear la tabla 'Personalportero' 
             String createPersonalporteroTableQuery = "CREATE TABLE IF NOT EXISTS personalportero (" 
                     + "id SERIAL PRIMARY KEY, "
                     + "horainicio VARCHAR(100), "
                     + "horafin VARCHAR(100), "
                     + "cedula VARCHAR(15) NOT NULL, "
                     + "nombre VARCHAR(100) NOT NULL, "
                     + "apellido VARCHAR(100), "
                     + "correo VARCHAR(100), "
                     + "turnossemana VARCHAR(100), "  // Añadir el campo turnossemana
                     + "tiempotrabajado VARCHAR(100)"  // Añadir el campo tiempotrabajado
                     + ")";
             statement.execute(createPersonalporteroTableQuery);
             System.out.println("Tabla Personal de Personal de portero' creada exitosamente!");
             
               // Crear la tabla 'Jefedeplanta' 
             String createJefedeplantaTableQuery = "CREATE TABLE IF NOT EXISTS jefedeplanta (" 
                     + "id SERIAL PRIMARY KEY, "
                     + "horainicio VARCHAR(100), "
                     + "horafin VARCHAR(100), "
                     + "cedula VARCHAR(15) NOT NULL, "
                     + "nombre VARCHAR(100) NOT NULL, "
                     + "apellido VARCHAR(100), "
                     + "correo VARCHAR(100), "
                     + "turnossemana VARCHAR(100), "  // Añadir el campo turnossemana
                     + "tiempotrabajado VARCHAR(100)"  // Añadir el campo tiempotrabajado
                     + ")";
             statement.execute(createJefedeplantaTableQuery);
             System.out.println("Tabla Personal de Personal de portero' creada exitosamente!");
             
               // Crear la tabla 'Personalarchivo' 
             String createPersonalarchivoTableQuery = "CREATE TABLE IF NOT EXISTS personalarchivo (" 
                     + "id SERIAL PRIMARY KEY, "
                     + "horainicio VARCHAR(100), "
                     + "horafin VARCHAR(100), "
                     + "cedula VARCHAR(15) NOT NULL, "
                     + "nombre VARCHAR(100) NOT NULL, "
                     + "apellido VARCHAR(100), "
                     + "correo VARCHAR(100), "
                     + "turnossemana VARCHAR(100), "  // Añadir el campo turnossemana
                     + "tiempotrabajado VARCHAR(100)"  // Añadir el campo tiempotrabajado
                     + ")";
             statement.execute(createPersonalarchivoTableQuery);
             System.out.println("Tabla Personal de Personal archivo' creada exitosamente!");
 
               // Crear la tabla 'Operadorcentral' 
             String createOperadorcentralTableQuery = "CREATE TABLE IF NOT EXISTS operadorcentral (" 
                     + "id SERIAL PRIMARY KEY, "
                     + "horainicio VARCHAR(100), "
                     + "horafin VARCHAR(100), "
                     + "cedula VARCHAR(15) NOT NULL, "
                     + "nombre VARCHAR(100) NOT NULL, "
                     + "apellido VARCHAR(100), "
                     + "correo VARCHAR(100), "
                     + "turnossemana VARCHAR(100), "  
                     + "tiempotrabajado VARCHAR(100)" 
                     + ")";
             statement.execute(createOperadorcentralTableQuery);
             System.out.println("Tabla Personal de Operadorcentral'creada exitosamente!");
 
               // Crear la tabla 'Supervisoraseo' 
             String createSupervisoraseoTableQuery = "CREATE TABLE IF NOT EXISTS supervisoraseo (" 
                     + "id SERIAL PRIMARY KEY, "
                     + "horainicio VARCHAR(100), "
                     + "horafin VARCHAR(100), "
                     + "cedula VARCHAR(15) NOT NULL, "
                     + "nombre VARCHAR(100) NOT NULL, "
                     + "apellido VARCHAR(100), "
                     + "correo VARCHAR(100), "
                     + "turnossemana VARCHAR(100), "  
                     + "tiempotrabajado VARCHAR(100)" 
                     + ")";
             statement.execute(createSupervisoraseoTableQuery);
             System.out.println("Tabla Personal de Operadorcentral'creada exitosamente!");
 
 
               // Crear la tabla 'Supervisoraseo' 
             String createCajeroTableQuery = "CREATE TABLE IF NOT EXISTS cajero (" 
                     + "id SERIAL PRIMARY KEY, "
                     + "horainicio VARCHAR(100), "
                     + "horafin VARCHAR(100), "
                     + "cedula VARCHAR(15) NOT NULL, "
                     + "nombre VARCHAR(100) NOT NULL, "
                     + "apellido VARCHAR(100), "
                     + "correo VARCHAR(100), "
                     + "turnossemana VARCHAR(100), "  
                     + "tiempotrabajado VARCHAR(100)" 
                     + ")";
             statement.execute(createCajeroTableQuery);
             System.out.println("Tabla Cajero'creada exitosamente!");
             
               // Crear la tabla 'Tecnico radiologia' 
             String createTecnicoradiologiaTableQuery = "CREATE TABLE IF NOT EXISTS tecnicoradiologia (" 
                     + "id SERIAL PRIMARY KEY, "
                     + "horainicio VARCHAR(100), "
                     + "horafin VARCHAR(100), "
                     + "cedula VARCHAR(15) NOT NULL, "
                     + "nombre VARCHAR(100) NOT NULL, "
                     + "apellido VARCHAR(100), "
                     + "correo VARCHAR(100), "
                     + "turnossemana VARCHAR(100), "  
                     + "tiempotrabajado VARCHAR(100)" 
                     + ")";
             statement.execute(createTecnicoradiologiaTableQuery);
             System.out.println("Tabla Tecnico radio logia'creada exitosamente!");
             
               // Crear la tabla 'Tecnico laboratorio' 
             String createTecnicolaboratorioTableQuery = "CREATE TABLE IF NOT EXISTS Tecnicolaboratorio (" 
                     + "id SERIAL PRIMARY KEY, "
                     + "horainicio VARCHAR(100), "
                     + "horafin VARCHAR(100), "
                     + "cedula VARCHAR(15) NOT NULL, "
                     + "nombre VARCHAR(100) NOT NULL, "
                     + "apellido VARCHAR(100), "
                     + "correo VARCHAR(100), "
                     + "turnossemana VARCHAR(100), "  
                     + "tiempotrabajado VARCHAR(100)" 
                     + ")";
             statement.execute(createTecnicolaboratorioTableQuery);
             System.out.println("Tabla Tecnico laboratorio 'creada exitosamente!");
 
               // Crear la tabla 'Fisioterapeuta' 
             String createFisioterapeutaTableQuery = "CREATE TABLE IF NOT EXISTS Fisioterapeuta (" 
                     + "id SERIAL PRIMARY KEY, "
                     + "horainicio VARCHAR(100), "
                     + "horafin VARCHAR(100), "
                     + "cedula VARCHAR(15) NOT NULL, "
                     + "nombre VARCHAR(100) NOT NULL, "
                     + "apellido VARCHAR(100), "
                     + "correo VARCHAR(100), "
                     + "turnossemana VARCHAR(100), "  
                     + "tiempotrabajado VARCHAR(100)" 
                     + ")";
             statement.execute(createFisioterapeutaTableQuery);
             System.out.println("Tabla Fisioterapeuta 'creada exitosamente!");
 
               // Crear la tabla 'Nutricionista' 
             String createNutricionistaTableQuery = "CREATE TABLE IF NOT EXISTS Nutricionista(" 
                     + "id SERIAL PRIMARY KEY, "
                     + "horainicio VARCHAR(100), "
                     + "horafin VARCHAR(100), "
                     + "cedula VARCHAR(15) NOT NULL, "
                     + "nombre VARCHAR(100) NOT NULL, "
                     + "apellido VARCHAR(100), "
                     + "correo VARCHAR(100), "
                     + "turnossemana VARCHAR(100), "  
                     + "tiempotrabajado VARCHAR(100)" 
                     + ")";
             statement.execute(createNutricionistaTableQuery);
             System.out.println("Tabla Nutricionista'creada exitosamente!");
                               
               // Crear la tabla 'Encargadofarmacia' 
             String createEncargadofarmaciaTableQuery = "CREATE TABLE IF NOT EXISTS Encargadofarmacia (" 
                     + "id SERIAL PRIMARY KEY, "
                     + "horainicio VARCHAR(100), "
                     + "horafin VARCHAR(100), "
                     + "cedula VARCHAR(15) NOT NULL, "
                     + "nombre VARCHAR(100) NOT NULL, "
                     + "apellido VARCHAR(100), "
                     + "correo VARCHAR(100), "
                     + "turnossemana VARCHAR(100), "  
                     + "tiempotrabajado VARCHAR(100)" 
                     + ")";
             statement.execute(createEncargadofarmaciaTableQuery);
             System.out.println("Tabla de Encargado farmacia' creada exitosamente!");
 
               // Crear la tabla 'voluntario' 
             String createVoluntarioTableQuery = "CREATE TABLE IF NOT EXISTS Voluntario (" 
                     + "id SERIAL PRIMARY KEY, "
                     + "horainicio VARCHAR(100), "
                     + "horafin VARCHAR(100), "
                     + "cedula VARCHAR(15) NOT NULL, "
                     + "nombre VARCHAR(100) NOT NULL, "
                     + "apellido VARCHAR(100), "
                     + "correo VARCHAR(100), "
                     + "turnossemana VARCHAR(100), "  
                     + "tiempotrabajado VARCHAR(100)" 
                     + ")";
             statement.execute(createVoluntarioTableQuery);
             System.out.println("Tabla de Voluntario' creada exitosamente!");
 
               // Crear la tabla 'voluntario' 
             String createPsicologoTableQuery = "CREATE TABLE IF NOT EXISTS Psicologo (" 
                     + "id SERIAL PRIMARY KEY, "
                     + "horainicio VARCHAR(100), "
                     + "horafin VARCHAR(100), "
                     + "cedula VARCHAR(15) NOT NULL, "
                     + "nombre VARCHAR(100) NOT NULL, "
                     + "apellido VARCHAR(100), "
                     + "correo VARCHAR(100), "
                     + "turnossemana VARCHAR(100), "  
                     + "tiempotrabajado VARCHAR(100)" 
                     + ")";
             statement.execute(createPsicologoTableQuery);
             System.out.println("Tabla de Psicologo' creada exitosamente!");

                     
                // Crear la tabla 'createCocineroTableQuery' si no existe
               String createCocineroTableQuery = "CREATE TABLE IF NOT EXISTS cocinero ("
                    + "id SERIAL PRIMARY KEY, "
                    + "horainicio VARCHAR(100), "
                    + "horafin VARCHAR(100), "
                    + "cedula VARCHAR(15) NOT NULL, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "apellido VARCHAR(100), "
                    + "correo VARCHAR(100), "
                    + "turnossemana VARCHAR(100), "  // Añadir el campo turnossemana
                    + "tiempotrabajado VARCHAR(100)"  // Añadir el campo tiempotrabajado
                    + ")";
                statement.execute(createCocineroTableQuery);
                System.out.println("Tabla 'Cocinero' creada exitosamente!");

            // Crear la tabla 'PersonalAseo' si no existe
            String createPersonalAseoTableQuery = "CREATE TABLE IF NOT EXISTS personal_aseo ("
                    + "id SERIAL PRIMARY KEY, "
                    + "hora VARCHAR(100), "
                    + "tiempotrabajado VARCHAR(100), "
                    + "cedula VARCHAR(15) NOT NULL, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "area VARCHAR(100), "
                    + "correo VARCHAR(100)"
                    + ")";
            statement.execute(createPersonalAseoTableQuery);
            System.out.println("Tabla 'personal_aseo' creada exitosamente!");
            
            // Crear la tabla 'PersonalAseo' si no existe
            String createSecretarioTableQuery = "CREATE TABLE IF NOT EXISTS Secretario("
                    + "id SERIAL PRIMARY KEY, "
                    + "hora VARCHAR(100), "
                    + "tiempotrabajado VARCHAR(100), "
                    + "cedula VARCHAR(15) NOT NULL, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "area VARCHAR(100), "
                    + "correo VARCHAR(100)"
                    + ")";
            statement.execute(createSecretarioTableQuery);
            System.out.println("Tabla 'Secretario' creada exitosamente!");
     

            // Crear la tabla 'doctor' si no existe
            String createDoctorTableQuery = "CREATE TABLE IF NOT EXISTS doctor ("
                    + "id SERIAL PRIMARY KEY, "
                    + "cedula VARCHAR(15) NOT NULL, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "especialidad VARCHAR(100), "
                    + "correo VARCHAR(100)"
                    + ")";
            statement.execute(createDoctorTableQuery);
            System.out.println("Tabla 'doctor' creada exitosamente!");

            // Crear la tabla 'paciente' con relación a la tabla 'doctor'
            String createPacienteTableQuery = "CREATE TABLE IF NOT EXISTS paciente ("
                    + "id SERIAL PRIMARY KEY, "
                    + "cedula VARCHAR(15) NOT NULL, "
                    + "nombre VARCHAR(100), "
                    + "apellido VARCHAR(100), "
                    + "edad INTEGER, "
                    + "direccion VARCHAR(255), "
                    + "correo VARCHAR(100), "
                    + "enfermedad VARCHAR(100), "
                    + "pronostico VARCHAR(255), "
                    + "doctor_id INTEGER, "
                    + "FOREIGN KEY (doctor_id) REFERENCES doctor(id)"
                    + ")";
            statement.execute(createPacienteTableQuery);
            System.out.println("Tabla 'paciente' creada exitosamente!");
            
           
            
            // metodo abstract

            
              // Crear la tabla para la clase Investigador
            String createInvestigadorTableQuery = "CREATE TABLE IF NOT EXISTS investigador ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "campoInvestigacion VARCHAR(100) NOT NULL, "
                    + "proyectosActivos INT, "
                    + "actividad VARCHAR(100), "
                    + "detalle VARCHAR(200), "
                    + "fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ")";
            statement.execute(createInvestigadorTableQuery);
            System.out.println("Tabla 'investigador' creada exitosamente!");
            
            
                    // Crear la tabla para la clase Instructor
            String createInstructorTableQuery = "CREATE TABLE IF NOT EXISTS instructor ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "nivelEducativo VARCHAR(50) NOT NULL, "
                    + "especialidadDocente VARCHAR(100) NOT NULL, "
                    + "proyectosActivos INT, "
                    + "actividad VARCHAR(100), "
                    + "detalle VARCHAR(200), "
                    + "fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ")";
            
            // Ejecutar la consulta
            statement.execute(createInstructorTableQuery);
            System.out.println("Tabla 'instructor' creada exitosamente!");
            
            
            // Crear la tabla para la clase CapacitacionMedico
            String createCapacitacionMedicoTableQuery = "CREATE TABLE IF NOT EXISTS capacitacion_medico ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "campoInvestigacion VARCHAR(100) NOT NULL, "
                    + "nivelEducativo VARCHAR(50) NOT NULL, "
                    + "especialidadDocente VARCHAR(100) NOT NULL, "
                    + "proyectosActivos INT, "
                    + "actividad VARCHAR(100), "
                    + "detalle VARCHAR(200), "
                    + "fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ")";

            // Ejecutar la consulta
            statement.execute(createCapacitacionMedicoTableQuery);
            System.out.println("Tabla 'capacitacion_medico' creada exitosamente!");
            
            
            // Crear la tabla para la clase GestionMantenimiento
                String createGestorMantenimientoTableQuery = "CREATE TABLE IF NOT EXISTS gestion_mantenimiento ("
                               + "id SERIAL PRIMARY KEY, "
                               + "nombre VARCHAR(100) NOT NULL, "
                               + "areaResponsabilidad VARCHAR(100) NOT NULL, "
                               + "tipoDeEquipo VARCHAR(100) NOT NULL, "
                               + "proyectosActivos INT, "
                               + "nivelEducativo VARCHAR(50) NOT NULL, "
                               + "especialidadDocente VARCHAR(100) NOT NULL, "
                               + "actividad VARCHAR(100), "
                               + "detalle VARCHAR(200), "
                               + "fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                               + ")";

           // Ejecutar la consulta
           statement.execute(createGestorMantenimientoTableQuery);
           System.out.println("Tabla 'gestion_mantenimiento' creada exitosamente!");

            
            
            
            // Crear la tabla para la clase JefeComunicaciones
            String createJefeComunicacionesTableQuery = "CREATE TABLE IF NOT EXISTS jefe_comunicaciones ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "canalDeComunicacion VARCHAR(100) NOT NULL, "
                    + "tipoDeMensaje VARCHAR(100) NOT NULL, "
                    + "proyectosActivos INT, "
                    + "nivelEducativo VARCHAR(50) NOT NULL, "
                    + "especialidad VARCHAR(100) NOT NULL, "
                    + "actividad VARCHAR(100), "
                    + "detalle VARCHAR(200), "
                    + "fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ")";

                    // Ejecutar la consulta
            statement.execute(createJefeComunicacionesTableQuery);
            System.out.println("Tabla 'jefe_comunicaciones' creada exitosamente!");


            // Crear la tabla para la clase AdministradorRedes
            String createAdministradorRedesTableQuery = "CREATE TABLE IF NOT EXISTS administrador_redes ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "nivelAcceso VARCHAR(100) NOT NULL, "
                    + "herramientasUtilizadas VARCHAR(100) NOT NULL, "
                    + "proyectosActivos INT, "
                    + "nivelEducativo VARCHAR(50) NOT NULL, "
                    + "especialidad VARCHAR(100) NOT NULL, "
                    + "actividad VARCHAR(100), "
                    + "detalle VARCHAR(200), "
                    + "fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ")";

            // Ejecutar la consulta para crear la tabla
            statement.execute(createAdministradorRedesTableQuery);
            System.out.println("Tabla 'administrador_redes' creada exitosamente!");
            
            // Crear la tabla para la clase CoordinadorBienestar
            String createCoordinadorBienestarTableQuery = "CREATE TABLE IF NOT EXISTS coordinador_bienestar ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "edad VARCHAR(10) NOT NULL, "
                    + "proyectosActivos INT, "
                    + "nivelEducativo VARCHAR(50) NOT NULL, "
                    + "especialidad VARCHAR(100) NOT NULL, "
                    + "actividad VARCHAR(100), "
                    + "detalle VARCHAR(200), "
                    + "fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ")";

            // Ejecutar la consulta para crear la tabla
            statement.execute(createCoordinadorBienestarTableQuery);
            System.out.println("Tabla 'coordinador_bienestar' creada exitosamente!");       

            
            // Crear la tabla para la clase AbogadoLaboral
            String createAbogadoLaboralTableQuery = "CREATE TABLE IF NOT EXISTS abogado_laboral ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "especialidadLegal VARCHAR(100) NOT NULL, "
                    + "proyectosActivos INT, "
                    + "nivelEducativo VARCHAR(50) NOT NULL, "
                    + "actividad VARCHAR(100), "
                    + "detalle VARCHAR(200), "
                    + "fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ")";

            // Ejecutar la consulta para crear la tabla
            statement.execute(createAbogadoLaboralTableQuery);
            System.out.println("Tabla 'abogado_laboral' creada exitosamente!");

            
            
              // Crear la tabla para la clase AuditorFinanciero
            String createAuditorFinancieroTableQuery = "CREATE TABLE IF NOT EXISTS auditor_financiero ("
                    + "id SERIAL PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "areaDeAuditoria VARCHAR(100) NOT NULL, "
                    + "proyectosActivos INT, "
                    + "nivelEducativo VARCHAR(50) NOT NULL, "
                    + "actividad VARCHAR(100), "
                    + "detalle VARCHAR(200), "
                    + "fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ")";

            // Ejecutar la consulta para crear la tabla
            statement.execute(createAuditorFinancieroTableQuery);
            System.out.println("Tabla 'auditor_financiero' creada exitosamente!");
            
            
            
            
            
             
            
            
            // Insertar datos según opción
            Scanner scanner = new Scanner(System.in);
            System.out.println("¿Qué datos desea insertar?");
            System.out.println("1. Registrar Paciente y Doctor");
            System.out.println("2. Registrar Personal de Aseo");
            System.out.println("3. Registrar Guardia de Seguridad");
            System.out.println("4. Registrar cocinero");
            System.out.println("5. Registrar Conductor de ambulancia");
            System.out.println("6. Registrar Personal de mantenimiento");
            System.out.println("7. Registrar Personal de porteria");
            System.out.println("8. Registrar jefe de planta");
            System.out.println("9. Registrar personal del archivo");
            System.out.println("10. Registrar Operador central");
            System.out.println("11. Registrar Supervisor aseo");
            System.out.println("12. Registrar Secretario");
            System.out.println("13. Registrar Cajero");
            System.out.println("14. Registrar Encargado de farmacia");
            System.out.println("15. Registrar Voluntario");
            System.out.println("16. Registrar Tecnico de radiologia");
            System.out.println("17. Registrar Tecnico laboratorio");
            System.out.println("18. Registrar Fisioterapeuta ");
            System.out.println("19. Registrar Nutricionista");
            System.out.println("20. Registrar Psicologo");
            System.out.println("21. Registrar investigador Médico");
            System.out.println("22. Registrar instructor medico");
            System.out.println("23. Registrar la capacitacion");
            System.out.println("24. Registrar Gestor de mantenimiento");
            System.out.println("25. Registrar Gestor de JefeComunicaciones");
            System.out.println("26. Registrar Administrador Redes ");
            System.out.println("27. Registrar Coordinador de Investigacion ");
            System.out.println("28. Registrar Coordinador de Bienestar ");
            System.out.println("29. Registrar Llenar datos para Abogado Laboral");
            System.out.println("30. Registrar Llenar datos para Auditor Financiero");
            System.out.println("31. Crear farmacia y agregar medicamentos");
            System.out.println("32. Crear enfermera y turnos");
            System.out.println("33. Agregar jefe y departamentos acargo" );
            System.out.println("34. Registrar laboratorio y exámenes de laboratorio" );
            System.out.println("35. Registrar  quirófano y cirugías" );
            System.out.println("36. Registrar  Ambulancia con ServicioEmergencia" );
            System.out.println("37. Registrar  sala de espera y citas médicas" );
            System.out.println("38. agregar área de radiología y estudios radiológicos" );
            System.out.println("39. agregar UCI y equipo médico" );
            System.out.println("40. agregar Historia Clínica y Diagnóstico" );
            System.out.println("41. Agregar Sala de Rehabilitación y Programas" );
            System.out.println("42. Registrar Unidad de Cuidado y sus Servicios" );
            System.out.println("43. Registrar Inventario y Suministros" );
            System.out.println("44. Registrar Sala de Recepción y Revistas" );
            System.out.println("45. Registrar Centro de Atención Urgente y Protocolos" );
            System.out.println("46. Agregar Área de Observación y Equipos de Monitoreo" );
            System.out.println("47. registrar el tipo de examen a realizar");
            System.out.println("48. registrar el tipo de tratamiento a aplicar:");
            System.out.println("49. registrar el Cama:");
            System.out.println("50. Seleccione el tipo de procedimiento:");
            System.out.println("51. Seleccione el tipo de procedimiento:");
            System.out.println("52. Ver interfaz de investigador Médico:");
            System.out.println("53. Ver interfaz de instructor Medico:");
            System.out.println("54. Ver datos del personal de aseo:");
            System.out.println("55. interfaz para Capacitacion Medica:");
            System.out.println("56. interfaz para Gestor Mantenimiento:");
            System.out.println("57. interfaz para Jefe de comunicaciones:");
            System.out.println("58. interfaz para Guardia de seguridad:");
            System.out.println("59. interfaz para Cocinero:");
            System.out.println("60. interfaz para Conductor ambulancia:");
            System.out.println("61. interfaz para Personal mantenimiento:");
            
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); 
            
             // Crear una instancia de InvestigadorMedicoDAOImpl
            InvestigadorMedicoDAO investigadorMedicoDAO = new InvestigadorMedicoDAOImpl(connection);
            JefeComunicaciones jefeComunicaciones = null; 

            switch (opcion) {
                case 1:
                    obtenerDatosPacienteYDoctor(connection);
                    System.out.println("Proceso completado. ¡Paciente y Doctor registrados exitosamente!");
                    break;
                case 2:
                    PersonalAseo personal = obtenerDatosPersonalAseo();
                    insertarPersonalAseo(connection, personal);
                    System.out.println("Proceso completado. ¡Personal de Aseo registrado exitosamente!");
                    break;
                    
                 case 3:
                    Guardiaseguridad guardia = obtenerDatosGuardiaseguridad();
                    insertarGuardiaseguridad(connection, guardia);
                    System.out.println("Proceso completado. ¡Guardia de Seguridad registrado exitosamente!");
                    break;
                    
                case 4:
                    Cocinero coci = obtenerDatosCocinero();
                    insertarCocinero(connection, coci);
                    System.out.println("Proceso completado. ¡Cocinero registrado exitosamente!");
                    break;
                    
                case 5:
                    Conductorambulancia ambulancia = obtenerDatosConductorambulancia();
                    insertarConductorambulancia (connection, ambulancia);
                    System.out.println("Proceso completado. ¡ambulancia registrado exitosamente!");
                    break;
                    
                 case 7:
                    Personalportero portero = obtenerDatosPersonalportero();
                    insertarPersonalportero (connection, portero);
                    System.out.println("Proceso completado. Personal de portero registrado exitosamente!");
                break;
                    
                    
                case 6:
                    Personalmantenimiento mantenimiento = obtenerDatosPersonalmantenimiento();
                    insertarPersonalmantenimiento (connection, mantenimiento);
                    System.out.println("Proceso completado. Personal de mantenimiento registrado exitosamente!");
                break;
                   
                case 8:
                    Jefedeplanta planta = obtenerDatosJefedeplanta();
                    insertarJefedeplanta (connection, planta);
                    System.out.println("Proceso completado. Personal de planta registrado exitosamente!");
                break;
                
                case 9:
                    Personalarchivo archivo = obtenerDatosPersonalarchivo();
                    insertarPersonalarchivo (connection, archivo);
                    System.out.println("Proceso completado. Personal de planta registrado exitosamente!");
                break;
                   
                case 10:
                    Operadorcentral central = obtenerDatosOperadorcentral();
                    insertarOperadorcentral (connection, central);
                    System.out.println("Proceso completado. Personal de planta registrado exitosamente!");
                break;
                
                case 11:
                    Supervisoraseo soraseo = obtenerDatosSupervisoraseo();
                    insertarSupervisoraseo (connection, soraseo);
                    System.out.println("Proceso completado. Supervisor aseo registrado exitosamente!");
                break;
                
                case 12:
                    Secretario cretario = obtenerDatosSecretario();
                    insertarSecretario (connection, cretario);
                    System.out.println("Proceso completado. Secretario registrado exitosamente!");
                break;
                
                case 13:
                    Cajero jero = obtenerDatosCajero();
                    insertarCajero (connection, jero);
                    System.out.println("Proceso completado. Secretario registrado exitosamente!");
                break;                
                
                case 14:
                    Encargadofarmacia farmacia = obtenerDatosEncargadofarmacia();
                    insertarEncargadofarmacia (connection, farmacia);
                    System.out.println("Proceso completado. Secretario registrado exitosamente!");
                break;
                
                case 15:
                    Voluntario tario = obtenerDatosVoluntario();
                    insertarVoluntario (connection, tario);
                    System.out.println("Proceso completado. Voluntario registrado exitosamente!");
                break;                
                
                case 16:
                    Tecnicoradiologia radiologia = obtenerDatosTecnicoradiologia();
                    insertarTecnicoradiologia (connection, radiologia);
                    System.out.println("Proceso completado. Tecnico radiologia registrado exitosamente!");
                break;                
                
                case 17:
                    Tecnicolaboratorio laboratorio = obtenerDatosTecnicolaboratorio();
                    insertarTecnicolaboratorio (connection, laboratorio);
                    System.out.println("Proceso completado. Tecnico laboratorio registrado exitosamente!");
                break;                
                
                case 18:
                    Fisioterapeuta terapeuta = obtenerDatosFisioterapeuta();
                    insertarFisioterapeuta (connection, terapeuta);
                    System.out.println("Proceso completado. Fisioterapeuta registrado exitosamente!");
                break;                
                
                case 19:
                    Nutricionista cionista = obtenerDatosNutricionista();
                    insertarNutricionista (connection, cionista);
                    System.out.println("Proceso completado. Fisioterapeuta registrado exitosamente!");
                break;                
                         
                case 20:
                    Psicologo cologo = obtenerDatosPsicologo();
                    insertarPsicologo (connection, cologo);
                    System.out.println("Proceso completado. Psicologo registrado exitosamente!");
                break;  
                
                case 21:
                    // Llenar datos para Investigador Médico
                    System.out.print("Ingrese el nombre del investigador médico: ");
                    String nombreMedico = scanner.nextLine();
                    System.out.print("Ingrese el campo de investigación: ");
                    String campoInvestigacionMedico = scanner.nextLine();
                    System.out.print("Ingrese el número de proyectos activos: ");
                    int proyectosActivosMedico = scanner.nextInt();
                    scanner.nextLine(); // Limpia el salto de línea después de nextInt()

                    // Crear un objeto InvestigadorMedico
                    InvestigadorMedico investigadorMedico = new InvestigadorMedico(nombreMedico, campoInvestigacionMedico, proyectosActivosMedico, connection);
                    
                    // Guardar el investigador usando la interfaz
                    investigadorMedicoDAO.guardarInvestigador(investigadorMedico);
                    System.out.println("Investigador médico guardado exitosamente.");

                    // Mostrar las actividades del investigador
                    investigadorMedico.disenarEstudio();
                    investigadorMedico.analizarDatos();
                    investigadorMedico.publicarInforme();
                    break;

                    case 52:
                    List<InvestigadorMedico> investigadores = investigadorMedicoDAO.obtenerTodosInvestigadores();
                    InvestigadorMedicoUI.getInstance(investigadores).setVisible(true); // Mostrar la interfaz gráfica con los datos
                    break;
                    
                    case 22:
                        // Llenar datos para Instructor Médico
                        System.out.print("Ingrese el nombre del instructor médico: ");
                        String nombreInstructor = scanner.nextLine();
                        System.out.print("Ingrese el nivel educativo del instructor: ");
                        String nivelEducativoInstructor = scanner.nextLine();
                        System.out.print("Ingrese la especialidad docente: ");
                        String especialidadDocenteInstructor = scanner.nextLine();
                        System.out.print("Ingrese el número de proyectos activos: ");
                        int proyectosActivosInstructor = scanner.nextInt();
                        scanner.nextLine(); // Consumir la nueva línea

                        System.out.print("Ingrese el detalle de la actividad: ");
                        String detalleActividad = scanner.nextLine(); // Obtener el detalle de la actividad

                        // Crear un objeto InstructorMedico
                        InstructorMedico instructorMedico = new InstructorMedico(0, nombreInstructor, nivelEducativoInstructor, especialidadDocenteInstructor, proyectosActivosInstructor, detalleActividad, connection);

                        // Guardar las actividades del instructor en la base de datos
                        instructorMedico.planificarCurso();
                        instructorMedico.evaluarEstudiantes();
                        instructorMedico.publicarNotas();
                    break;

                    
                    case 53:  
                     // Instanciar el DAO e interfaz
                    InstructorMedicoDAO instructorMedicoDAO = new InstructorMedicoDAOImpl(connection);
                    List<InstructorMedico> instructores = instructorMedicoDAO.obtenerTodosInstructores();
                    new InstructorMedicoUI(instructores).setVisible(true);                 
                    break;
                  
                    
                  case 54:
                    System.out.println("Abriendo PersonalAseoUI...");
                    new PersonalAseoUI(connection);
                  break;
                  
                    ///// verificar desde acá
                    
                    case 23: 
                        // Llenar datos para Capacitación Médica
                        System.out.print("Ingrese el nombre del capacitador médico: ");
                        String nombreCapacitador = scanner.nextLine();
                        System.out.print("Ingrese el campo de investigación: ");
                        String campoInvestigacionCapacitador = scanner.nextLine();
                        System.out.print("Ingrese el nivel educativo del capacitador: ");
                        String nivelEducativoCapacitador = scanner.nextLine();
                        System.out.print("Ingrese la especialidad docente: ");
                        String especialidadDocenteCapacitador = scanner.nextLine();
                        System.out.print("Ingrese el número de cedula activos: ");
                        int proyectosActivosCapacitador = scanner.nextInt();
                        scanner.nextLine(); // Consumir la nueva línea
                        
                        // Crear un objeto CapacitacionMedico
                        CapacitacionMedico capacitacionMedico = new CapacitacionMedico(
                                nombreCapacitador,
                                campoInvestigacionCapacitador,
                                proyectosActivosCapacitador,
                                nivelEducativoCapacitador,
                                especialidadDocenteCapacitador,
                                connection
                        );

                        // Organizar taller y registrar actividades
                        capacitacionMedico.organizarTaller();
                        capacitacionMedico.monitorearProgreso();
                        capacitacionMedico.InformeProgreso();
                    break;
                 
                   case 55:
                    // Instanciar el DAO e interfaz para CapacitacionMedica
                    CapacitacionMedicaDAO capacitacionMedicaDAO = new CapacitacionMedicaDAOImpl(connection);

                    // Obtener todos los datos de capacitaciones
                    List<CapacitacionMedico> capacitaciones = capacitacionMedicaDAO.getAllCapacitaciones();

                    // Mostrar la interfaz gráfica con los datos obtenidos
                    CapacitacionMedicaUI.getInstance(capacitaciones).setVisible(true);
                    break;
                        
                        
                    case 24: 
                        // Llenar datos para Gestor de Mantenimiento
                        System.out.print("Ingrese el nombre del gestor de mantenimiento: ");
                        String nombreGestor = scanner.nextLine();

                        System.out.print("Ingrese el área de responsabilidad: ");
                        String areaResponsabilidadGestor = scanner.nextLine();

                        System.out.print("Ingrese el tipo de equipo: ");
                        String tipoDeEquipoGestor = scanner.nextLine();

                        System.out.print("Ingrese el número de proyectos activos: ");
                        int proyectosActivosGestor = scanner.nextInt();
                        scanner.nextLine(); // Consumir la nueva línea

                        System.out.print("Ingrese el nivel educativo del gestor: ");
                        String nivelEducativoGestor = scanner.nextLine();

                        System.out.print("Ingrese la especialidad docente: ");
                        String especialidadDocenteGestor = scanner.nextLine();

                        // Crear un objeto GestorMantenimientoPlanta
                        GestorMantenimientoPlanta gestorMantenimiento = new GestorMantenimientoPlanta(
                                nombreGestor,
                                areaResponsabilidadGestor,
                                tipoDeEquipoGestor,
                                proyectosActivosGestor,
                                nivelEducativoGestor,
                                especialidadDocenteGestor,
                                connection
                        );

                        // Programar mantenimiento y registrar actividades
                        gestorMantenimiento.programarMantenimiento();
                        gestorMantenimiento.repararEquipo();
                    break;
                    
                    
                    case 56:
                            GestorMantenimientoDAO gestorMantenimientoDAO = new GestorMantenimientoDAOImpl(connection);
                            List<GestorMantenimientoPlanta> gestores = gestorMantenimientoDAO.obtenerTodosLosGestores();
                            new GestorMantenimientoUI(connection).setVisible(true);
                    break;

                    
                    case 25:
                     // Llenar datos para Jefe de Comunicaciones
                     System.out.print("Ingrese el nombre del jefe de comunicaciones: ");
                     String nombreJefeComunicaciones = scanner.nextLine();

                     System.out.print("Ingrese el canal de comunicación (por ejemplo: Email, Redes Sociales, etc.): ");
                     String canalDeComunicacion = scanner.nextLine();

                     System.out.print("Ingrese el tipo de mensaje (por ejemplo: Noticias, Alertas, Anuncios): ");
                     String tipoDeMensaje = scanner.nextLine();

                     System.out.print("Ingrese el número de cedula: ");
                     int proyectosActivosComunicaciones = scanner.nextInt();
                     scanner.nextLine(); // Consumir la nueva línea

                     System.out.print("Ingrese el nivel educativo del jefe de comunicaciones: ");
                     String nivelEducativoComunicaciones = scanner.nextLine();

                     System.out.print("Ingrese la especialidad en comunicaciones: ");
                     String especialidadComunicaciones = scanner.nextLine();

                     // Crear un objeto JefeComunicaciones
                     jefeComunicaciones = new JefeComunicaciones(
                             nombreJefeComunicaciones,
                             canalDeComunicacion,
                             tipoDeMensaje,
                             proyectosActivosComunicaciones,
                             nivelEducativoComunicaciones,
                             especialidadComunicaciones,
                             connection
                     );

                     // Realizar actividades relacionadas con comunicaciones y registrar actividades
                     jefeComunicaciones.redactarComunicado();
                     jefeComunicaciones.gestionarRedesSociales();
                     break;

                     
                  case 57:
                    JefeComunicacionesDAO jefeComunicacionesDAO = new JefeComunicacionesDAOImpl(connection);
                    List<JefeComunicaciones> jefes = jefeComunicacionesDAO.obtenerTodosLosJefesComunicaciones();
                    // Crea la instancia de JefeComunicacionesUI pasando la lista completa de jefes
                    JefeComunicacionesUI ui = new JefeComunicacionesUI(jefes);
                    ui.setVisible(true);
                    break;


                    case 26:
                    // Llenar datos para Administrador de Redes
                    System.out.print("Ingrese el nombre del administrador de red del hospital: ");
                    String nombreAdministradorRedes = scanner.nextLine();

                    System.out.print("Ingrese el nivel de acceso (por ejemplo: Alto, Medio, Bajo): ");
                    String nivelAccesoAdministrador = scanner.nextLine();

                    System.out.print("Ingrese las herramientas utilizadas (por ejemplo: Wireshark, Cisco Packet Tracer): ");
                    String herramientasUtilizadas = scanner.nextLine();

                    System.out.print("Ingrese el número de proyectos activos: ");
                    int proyectosActivosRedes = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea

                    System.out.print("Ingrese el nivel educativo del administrador de redes: ");
                    String nivelEducativoRedes = scanner.nextLine();

                    System.out.print("Ingrese la especialidad del administrador de redes (por ejemplo: Redes y Comunicaciones, Seguridad Informática): ");
                    String especialidadRedes = scanner.nextLine();

                    // Crear un objeto AdministradorRedes
                    AdministradorRedes administradorRedes = new AdministradorRedes(
                            nombreAdministradorRedes,
                            nivelAccesoAdministrador,
                            herramientasUtilizadas,
                            proyectosActivosRedes,
                            nivelEducativoRedes,
                            especialidadRedes,
                            connection
                    );

                    // Realizar actividades relacionadas con la administración de redes y registrar actividades
                    administradorRedes.mantenerSistema();
                    administradorRedes.resolverIncidente();
                    break;
                    
                    
                    
                     case 58:
                        System.out.println("Abriendo GuardiaseguridadUI...");
                        new GuardiaseguridadUI(connection);
                    break;
                    
                    
                    case 59:
                        System.out.println("Abriendo CocineroUI...");
                        new CocineroUI (connection);
                    break;
                    
                    
                    case 60:
                        System.out.println("Abriendo ConductorambulanciaUI...");
                        new ConductorambulanciaUI (connection);
                    break;
                    
                    case 61:
                        System.out.println("Abriendo PersonalmantenimientoUI...");
                        new PersonalmantenimientoUI (connection);
                    break;
                    
                    
                    
                    
                    
                    
                    
                     
                  

                    
                    
                    
                    
                    
                    case 27:
                    // Llenar datos para Coordinador de Investigación
                    System.out.print("Ingrese el nombre del coordinador de investigación: ");
                    String nombreCoordinador = scanner.nextLine();

                    System.out.print("Ingrese el nombre del proyecto: ");
                    String nombreProyecto = scanner.nextLine();

                    System.out.print("Ingrese el presupuesto del proyecto (por ejemplo: 5000 USD): ");
                    String presupuesto = scanner.nextLine();

                    System.out.print("Ingrese el número de proyectos activos: ");
                    int proyectosActivos = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea

                    System.out.print("Ingrese el nivel educativo del coordinador de investigación: ");
                    String nivelEducativo = scanner.nextLine();

                    System.out.print("Ingrese la especialidad del coordinador de investigación (por ejemplo: Inteligencia Artificial, Seguridad Informática): ");
                    String especialidad = scanner.nextLine();

                    // Crear un objeto CoordinadorInvestigacion
                    CoordinadorInvestigacion coordinadorInvestigacion = new CoordinadorInvestigacion(
                            nombreCoordinador,
                            nombreProyecto,
                            presupuesto,
                            proyectosActivos,
                            nivelEducativo,
                            especialidad,
                            connection
                    );
                    // Realizar actividades relacionadas con la coordinación de investigación y registrar actividades
                    coordinadorInvestigacion.asignarRecursos();
                    coordinadorInvestigacion.establecerCronograma();
                    coordinadorInvestigacion.evaluarProgreso();
                    break;
                    
                    
                    case 28:
                    // Llenar datos para Coordinador de Bienestar
                    System.out.print("Ingrese el nombre del coordinador de bienestar: ");
                    String nombreCoordinadorBienestar = scanner.nextLine();

                    System.out.print("Ingrese la edad del coordinador de bienestar: ");
                    String edad = scanner.nextLine();

                    System.out.print("Ingrese el número de proyectos activos: ");
                    int proyectosActivosBienestar = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea

                    System.out.print("Ingrese el nivel educativo del coordinador de bienestar: ");
                    String nivelEducativoBienestar = scanner.nextLine();

                    System.out.print("Ingrese la especialidad del coordinador de bienestar (por ejemplo: Psicología, Trabajo Social): ");
                    String especialidadBienestar = scanner.nextLine();

                    // Crear un objeto CoordinadorBienestar
                    CoordinadorBienestar coordinadorBienestar = new CoordinadorBienestar(
                            nombreCoordinadorBienestar,
                            edad,
                            proyectosActivosBienestar,
                            nivelEducativoBienestar,
                            especialidadBienestar,
                            connection
                    );

                    // Llamar a los métodos para realizar actividades relacionadas con el bienestar y registrar actividades
                    coordinadorBienestar.organizarActividadesRecreativas();
                    coordinadorBienestar.ofrecerAsesoramiento();

                    // Confirmación de registro
                    System.out.println("Coordinador de Bienestar registrado y actividades realizadas con éxito.");
                    break;
                    
                    case 29:
                     // Llenar datos para Abogado Laboral
                    System.out.print("Ingrese el nombre del abogado laboral: ");
                    String nombreAbogadoLaboral = scanner.nextLine();

                    System.out.print("Ingrese la especialidad legal del abogado laboral (por ejemplo: Derecho Laboral): ");
                    String especialidadLegalLaboral = scanner.nextLine();

                    System.out.print("Ingrese el número de proyectos activos: ");
                    int proyectosActivosLaboral = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea

                    System.out.print("Ingrese el nivel educativo del abogado laboral (por ejemplo: Licenciatura, Maestría): ");
                    String nivelEducativoLaboral = scanner.nextLine();

                    // Crear un objeto AbogadoLaboral con los datos ingresados
                    AbogadoLaboral abogadoLaboral = new AbogadoLaboral(
                            nombreAbogadoLaboral,
                            especialidadLegalLaboral,
                            proyectosActivosLaboral,
                            nivelEducativoLaboral,
                            connection // Conexión a la base de datos
                    );

                    // Llamar a los métodos para realizar actividades relacionadas con el asesoramiento y el análisis
                    abogadoLaboral.analizarNormativa();
                    abogadoLaboral.asesorarDirectivos();

                    // Confirmación de registro
                    System.out.println("Abogado Laboral registrado y actividades realizadas con éxito.");
                    break;
                    
                    case 30:
                    // Llenar datos para Auditor Financiero
                    System.out.print("Ingrese el nombre del auditor financiero: ");
                    String nombreAuditorFinanciero = scanner.nextLine();

                    System.out.print("Ingrese el área de auditoría del auditor financiero (por ejemplo: Auditoría Financiera): ");
                    String areaDeAuditoriaFinanciero = scanner.nextLine();

                    System.out.print("Ingrese el número de proyectos activos: ");
                    int proyectosActivosFinanciero = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea

                    System.out.print("Ingrese el nivel educativo del auditor financiero (por ejemplo: Licenciatura, Maestría): ");
                    String nivelEducativoFinanciero = scanner.nextLine();

                    // Crear un objeto AuditorFinanciero con los datos ingresados
                    AuditorFinanciero auditorFinanciero = new AuditorFinanciero(
                            nombreAuditorFinanciero,
                            areaDeAuditoriaFinanciero,
                            proyectosActivosFinanciero,
                            nivelEducativoFinanciero,
                            connection // Conexión a la base de datos
                    );

                    // Llamar a los métodos para realizar actividades relacionadas con la auditoría y la emisión de reportes
                    auditorFinanciero.realizarAuditoria();
                    auditorFinanciero.emitirReporte();

                    // Confirmación de registro
                    System.out.println("Auditor Financiero registrado y actividades realizadas con éxito.");
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
                 
                    case 31:

                         // Llenar datos para Farmacia
                            System.out.print("Ingrese el nombre de la farmacia: ");
                            String nombreFarmacia = scanner.nextLine();
                            System.out.print("Ingrese la dirección de la farmacia: ");
                            String direccionFarmacia = scanner.nextLine();

                            // Crear un objeto Farmacia y guardarlo en la base de datos
                            Farmacia nuevaFarmacia = new Farmacia(nombreFarmacia, direccionFarmacia);
                            nuevaFarmacia.guardarEnBaseDatos(connection);

                            // Llenar datos para Medicamentos
                            boolean agregarMedicamentos = true;
                            while (agregarMedicamentos) {
                                System.out.print("Ingrese el nombre del medicamento: ");
                                String nombreMedicamento = scanner.nextLine();
                                System.out.print("Ingrese el precio del medicamento (por ejemplo: 2000 para 2,000): ");
                                double precioMedicamento = scanner.nextDouble();
                                scanner.nextLine(); // Consumir la nueva línea

                                // Crear un objeto Medicamento y guardarlo en la base de datos
                                Medicamento nuevoMedicamento = new Medicamento(nombreMedicamento, precioMedicamento, nuevaFarmacia.getId());
                                nuevoMedicamento.guardarEnBaseDatos(connection);

                                System.out.print("¿Desea agregar otro medicamento? (s/n): ");
                                String respuesta = scanner.nextLine();
                                if (respuesta.equalsIgnoreCase("n")) {
                                    agregarMedicamentos = false;
                                }
                            }
                            break;
                        
                        case 32: // Opción para agregar enfermeras y turnos
                    // Llenar datos para la enfermera
                    System.out.print("Ingrese el nombre de la enfermera: ");
                    String nombreEnfermera = scanner.nextLine();
                    System.out.print("Ingrese el apellido de la enfermera: ");
                    String apellidoEnfermera = scanner.nextLine();

                    // Crear un objeto Enfermera y guardarlo en la base de datos
                    Enfermera nuevaEnfermera = new Enfermera(nombreEnfermera, apellidoEnfermera);
                    nuevaEnfermera.guardarEnBaseDatos(connection);

                    // Llenar datos para turnos
                    boolean agregarTurnos = true;
                    while (agregarTurnos) {
                        System.out.print("Ingrese la hora de inicio del turno (o 'fin' para terminar): ");
                        String horaInicio = scanner.nextLine();
                        if (horaInicio.equalsIgnoreCase("fin")) {
                            agregarTurnos = false;
                            break;
                        }

                        System.out.print("Ingrese la hora de fin del turno: ");
                        String horaFin = scanner.nextLine();

                        // Crear un objeto Turno y guardarlo en la base de datos
                        Turno nuevoTurno = new Turno(horaInicio, horaFin, nuevaEnfermera.getId());
                        nuevoTurno.guardarEnBaseDatos(connection);
                    }
                    break;
                    
                    
                    case 33: 
                    // Llenar datos para el administrativo
                    System.out.print("Ingrese el nombre del administrativo: ");
                    String nombreAdministrativo = scanner.nextLine();
                    System.out.print("Ingrese el apellido del administrativo: ");
                    String apellidoAdministrativo = scanner.nextLine();

                    // Crear un objeto Administrativo y guardarlo en la base de datos
                    Administrativo nuevoAdministrativo = new Administrativo(nombreAdministrativo, apellidoAdministrativo);
                    nuevoAdministrativo.guardarEnBaseDatos(connection);

                    // Llenar datos para departamentos
                    boolean agregarDepartamentos = true;
                    while (agregarDepartamentos) {
                        System.out.print("Ingrese el nombre del departamento acargo  (o 'fin' para terminar): ");
                        String nombreDepartamento = scanner.nextLine();
                        if (nombreDepartamento.equalsIgnoreCase("fin")) {
                            agregarDepartamentos = false;
                            break;
                }

                // Crear un objeto Departamento y guardarlo en la base de datos
                Departamento nuevoDepartamento = new Departamento(nombreDepartamento, nuevoAdministrativo.getId());
                nuevoDepartamento.guardarEnBaseDatos(connection);
            }
            break;
            
            
                case 34: 
               
                System.out.print("Ingrese el nombre del laboratorio: ");
                String nombreLaboratorio = scanner.nextLine();
                System.out.print("Ingrese la ubicación del laboratorio: ");
                String ubicacionLaboratorio = scanner.nextLine();

                // Crear un objeto Laboratorio y guardarlo en la base de datos
                Laboratorio nuevoLaboratorio = new Laboratorio(nombreLaboratorio, ubicacionLaboratorio);
                nuevoLaboratorio.guardarEnBaseDatos(connection);

                // Llenar datos para exámenes de laboratorio
                boolean agregarExamenes = true;
                while (agregarExamenes) {
                    System.out.print("Ingrese el nombre del examen de laboratorio (o 'fin' para terminar): ");
                    String nombreExamen = scanner.nextLine();
                    if (nombreExamen.equalsIgnoreCase("fin")) {
                        agregarExamenes = false;
                        break;
                    }

                    System.out.print("Ingrese el costo del examen: ");
                    double costoExamen = scanner.nextDouble();
                    scanner.nextLine(); // Consumir la nueva línea

                    // Crear un objeto ExamenLaboratorio y guardarlo en la base de datos
                    ExamenLaboratorio nuevoExamen = new ExamenLaboratorio(nombreExamen, costoExamen, nuevoLaboratorio.getId());
                    nuevoExamen.guardarEnBaseDatos(connection);
                }
                break;
                
                case 35: 
                // Llenar datos para el quirófano
                System.out.print("Ingrese el nombre del quirófano: ");
                String nombreQuirofano = scanner.nextLine();
                System.out.print("Ingrese la ubicación del quirófano: ");
                String ubicacionQuirofano = scanner.nextLine();

                // Crear un objeto Quirofano y guardarlo en la base de datos
                Quirofano nuevoQuirofano = new Quirofano(nombreQuirofano, ubicacionQuirofano);
                nuevoQuirofano.guardarEnBaseDatos(connection);

                // Llenar datos para cirugías
                boolean agregarCirugias = true;
                while (agregarCirugias) {
                    System.out.print("Ingrese el tipo de cirugía (o 'fin' para terminar): ");
                    String tipoCirugia = scanner.nextLine();
                    if (tipoCirugia.equalsIgnoreCase("fin")) {
                        agregarCirugias = false;
                        break;
                    }

                    System.out.print("Ingrese la fecha de la cirugía (formato: YYYY-MM-DD): ");
                    String fechaCirugia = scanner.nextLine();

                    // Crear un objeto Cirugia y guardarlo en la base de datos
                    Cirugia nuevaCirugia = new Cirugia(tipoCirugia, fechaCirugia, nuevoQuirofano.getId());
                    nuevaCirugia.guardarEnBaseDatos(connection);
                }
                break;
                
                
                case 36:
                        // Agregar CarroAmbulancia
                        System.out.print("Ingrese la placa del carro ambulancia: ");
                        String placa = scanner.nextLine();
                        System.out.print("Ingrese el modelo del carro ambulancia: ");
                        String modelo = scanner.nextLine();

                        CarroAmbulancia carroAmbulancia = new CarroAmbulancia(placa, modelo);
                        carroAmbulancia.guardarEnBaseDatos(connection);

                        // Agregar ServicioEmergencia asociado al CarroAmbulancia
                        boolean agregarServicios = true;
                        while (agregarServicios) {
                            System.out.print("Ingrese la descripción del servicio de emergencia: ");
                            String descripcion = scanner.nextLine();
                            System.out.print("Ingrese la fecha del servicio de emergencia (DD-MM-YYYY): ");
                            String fecha = scanner.nextLine();

                            ServicioEmergencia servicio = new ServicioEmergencia(descripcion, fecha, carroAmbulancia.getId());
                            servicio.guardarEnBaseDatos(connection);

                            System.out.print("¿Desea agregar otro servicio de emergencia? (s/n): ");
                            String respuesta = scanner.nextLine();
                            if (respuesta.equalsIgnoreCase("n")) {
                                agregarServicios = false;
                            }
                        }
                        break;
                        
                        
                        case 37: // Opción para agregar sala de espera y citas médicas
                // Llenar datos para la sala de espera
                System.out.print("Ingrese el nombre de la sala de espera: ");
                String nombreSalaEspera = scanner.nextLine();
                System.out.print("Ingrese la capacidad de la sala de espera: ");
                int capacidadSalaEspera = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea

                // Crear un objeto SalaEspera y guardarlo en la base de datos
                SalaEspera nuevaSalaEspera = new SalaEspera(nombreSalaEspera, capacidadSalaEspera);
                nuevaSalaEspera.guardarEnBaseDatos(connection);

                // Llenar datos para las citas médicas
                boolean agregarCitas = true;
                while (agregarCitas) {
                    System.out.print("Ingrese la fecha de la cita médica (formato: YYYY-MM-DD): ");
                    String fechaCita = scanner.nextLine();
                    System.out.print("Ingrese la hora de la cita médica (formato: HH:MM): ");
                    String horaCita = scanner.nextLine();

                    // Crear un objeto CitaMedica y guardarlo en la base de datos
                    CitaMedica nuevaCita = new CitaMedica(fechaCita, horaCita, nuevaSalaEspera.getId());
                    nuevaCita.guardarEnBaseDatos(connection);

                    System.out.print("¿Desea agregar otra cita médica? (s/n): ");
                    String respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("n")) {
                        agregarCitas = false;
                    }
                }
                break;
                
           
                case 38: 
                // Llenar datos para el área de radiología
                System.out.print("Ingrese el nombre del área de radiología: ");
                String nombreAreaRadiologia = scanner.nextLine();

                // Crear un objeto AreaRadiologia y guardarlo en la base de datos
                AreaRadiologia nuevaAreaRadiologia = new AreaRadiologia(nombreAreaRadiologia);
                nuevaAreaRadiologia.guardarEnBaseDatos(connection);

                // Llenar datos para los estudios radiológicos
                boolean agregarEstudios = true;
                while (agregarEstudios) {
                    System.out.print("Ingrese el tipo de estudio radiológico (por ejemplo: Rayos X, Tomografía): ");
                    String tipoEstudio = scanner.nextLine();
                    System.out.print("Ingrese la fecha del estudio radiológico (formato: YYYY-MM-DD): ");
                    String fechaEstudio = scanner.nextLine();

                    // Crear un objeto EstudioRadiologico y guardarlo en la base de datos
                    EstudioRadiologico nuevoEstudio = new EstudioRadiologico(tipoEstudio, fechaEstudio, nuevaAreaRadiologia.getId());
                    nuevoEstudio.guardarEnBaseDatos(connection);

                    System.out.print("¿Desea agregar otro estudio radiológico? (s/n): ");
                    String respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("n")) {
                        agregarEstudios = false;
                    }
                }
                break;
                

                case 39: 
                // Llenar datos para la UCI
                System.out.print("Ingrese el nombre de la Unidad de Cuidados Intensivos: ");
                String nombreUCI = scanner.nextLine();
                System.out.print("Ingrese la ubicación de la UCI: ");
                String ubicacionUCI = scanner.nextLine();

                // Llenar datos para el equipo médico
                System.out.print("Ingrese el tipo de equipo médico (por ejemplo: Ventilador, Monitor Cardíaco): ");
                String tipoEquipo = scanner.nextLine();
                System.out.print("Ingrese el fabricante del equipo médico: ");
                String fabricanteEquipo = scanner.nextLine();

                // Crear un objeto UnidadCuidadosIntensivos con su equipo médico y guardarlo en la base de datos
                UnidadCuidadosIntensivos nuevaUCI = new UnidadCuidadosIntensivos(nombreUCI, ubicacionUCI, tipoEquipo, fabricanteEquipo);
                nuevaUCI.guardarEnBaseDatos(connection);
                break;

               case 40:
                // Llenar datos para la Historia Clínica
                System.out.print("Ingrese el nombre del paciente: ");
                String nombrePaciente = scanner.nextLine();

                // Validar la fecha de apertura
                boolean fechaValida = false;
                String fechaApertura = "";

                while (!fechaValida) {
                    System.out.print("Ingrese la fecha de apertura de la historia clínica (AAAA-MM-DD) ejemplo 2023-12-23: ");
                    fechaApertura = scanner.nextLine();
                    try {
                        Date.valueOf(fechaApertura); // Esto lanzará una excepción si el formato es incorrecto
                        fechaValida = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Fecha inválida. Asegúrese de que está en el formato AAAA-MM-DD.");
                    }
                }

                // Llenar datos para el diagnóstico
                System.out.print("Ingrese la descripción del diagnóstico: ");
                String descripcionDiagnostico = scanner.nextLine();
                System.out.print("Ingrese el nombre del médico responsable: ");
                String nombreMedicoDiagnostico = scanner.nextLine();  // Cambié el nombre de la variable

                // Crear un objeto HistoriaClinica con su diagnóstico y guardarlo en la base de datos
                HistoriaClinica nuevaHistoriaClinica = new HistoriaClinica(nombrePaciente, fechaApertura, descripcionDiagnostico, nombreMedicoDiagnostico);
                nuevaHistoriaClinica.guardarEnBaseDatos(connection);
                break;
                
                case 41: 
             // Llenar datos para la Sala de Rehabilitación
             System.out.print("Ingrese el nombre de la sala de rehabilitación: ");
             String nombreSala = scanner.nextLine();
             System.out.print("Ingrese la ubicación de la sala: ");
             String ubicacionSala = scanner.nextLine();

             // Crear un objeto SalaRehabilitacion
             SalaRehabilitacion nuevaSala = new SalaRehabilitacion(nombreSala, ubicacionSala);

             // Llenar datos para los Programas de Rehabilitación
             boolean agregarProgramas = true;
             while (agregarProgramas) {
                 System.out.print("Ingrese el nombre del programa de rehabilitación: ");
                 String nombrePrograma = scanner.nextLine();
                 System.out.print("Ingrese la descripción del programa de rehabilitación: ");
                 String descripcionPrograma = scanner.nextLine();

                 // Agregar el programa a la sala
                 nuevaSala.agregarProgramaRehabilitacion(nombrePrograma, descripcionPrograma);

                 System.out.print("¿Desea agregar otro programa de rehabilitación? (s/n): ");
                 String respuesta = scanner.nextLine();
                 if (respuesta.equalsIgnoreCase("n")) {
                     agregarProgramas = false;
                 }
             }

            // Guardar la Sala en la base de datos
            nuevaSala.guardarEnBaseDatos(connection);
            break;
            
                   
           case 42: // Opción para agregar Unidad de Cuidado y sus Servicios
                // Llenar datos para la Unidad de Cuidado
                System.out.print("Ingrese el nombre de la unidad de cuidado: ");
                String nombreUnidad = scanner.nextLine();
                System.out.print("Ingrese la dirección de la unidad de cuidado: ");
                String direccionUnidad = scanner.nextLine();

                // Crear un objeto Unidad de Cuidado    
                UnidadDeCuidado nuevaUnidad = new UnidadDeCuidado(nombreUnidad, direccionUnidad);

                // Declarar la variable continuarAgregandoServicios aquí
                boolean continuarAgregandoServicios = true; 
                while (continuarAgregandoServicios) {
                    System.out.print("Ingrese el nombre del servicio: ");
                    String nombreServicio = scanner.nextLine();
                    System.out.print("Ingrese el costo del servicio: ");
                    double costoServicio = scanner.nextDouble();
                    scanner.nextLine(); // Consumir la nueva línea
                    System.out.print("Ingrese el nombre del personal encargado: ");
                    String personalEncargado = scanner.nextLine();
                    System.out.print("Ingrese la duración del servicio en minutos: ");
                    int duracionServicio = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea

                    // Crear un objeto Servicio y agregarlo a la Unidad de Cuidado
                    Servicio nuevoServicio = new Servicio(nombreServicio, costoServicio, personalEncargado, duracionServicio);
                    nuevaUnidad.agregarServicio(nuevoServicio);

                    System.out.print("¿Desea agregar otro servicio? (s/n): ");
                    String respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("n")) {
                        continuarAgregandoServicios = false; // Terminar el bucle si el usuario dice que no
                    }
                }

                // Guardar la Unidad de Cuidado y sus Servicios en la base de datos
                nuevaUnidad.guardarEnBaseDatos(connection);
                break;
                
               case 43: // Opción para agregar Inventario y Suministros
            // Llenar datos para el Inventario
            System.out.print("Ingrese el nombre del inventario: ");
            String nombreInventario = scanner.nextLine();

            // Crear un objeto Inventario
            Inventario nuevoInventario = new Inventario(nombreInventario);

            // Llenar datos para los Suministros
            boolean agregarSuministros = true;
            while (agregarSuministros) {
                System.out.print("Ingrese el nombre del suministro: ");
                String nombreSuministro = scanner.nextLine();
                System.out.print("Ingrese la cantidad del suministro: ");
                double cantidadSuministro = scanner.nextDouble();
                scanner.nextLine(); // Consumir la nueva línea
                System.out.print("Ingrese la unidad de medida del suministro: ");
                String unidadMedida = scanner.nextLine();

                // Crear un objeto Suministro y agregarlo al Inventario
                Suministro nuevoSuministro = new Suministro(nombreSuministro, cantidadSuministro, unidadMedida);
                nuevoInventario.agregarSuministro(nuevoSuministro);

                System.out.print("¿Desea agregar otro suministro? (s/n): ");
                String respuesta = scanner.nextLine();
                if (respuesta.equalsIgnoreCase("n")) {
                    agregarSuministros = false;
                }
            }

            // Guardar el Inventario y sus Suministros en la base de datos
            nuevoInventario.guardarEnBaseDatos(connection);
            break;
            
            
                case 44:
                // Llenar datos para la Sala de Recepción
                System.out.print("Ingrese el nombre de la sala de recepción: ");
                String nombreSalaRecepcion = scanner.nextLine();

                // Crear un objeto Sala de Recepción
                SalaRecepcion nuevaSalaRecepcion = new SalaRecepcion(nombreSalaRecepcion);

                // Llenar datos para las Revistas
                boolean agregarRevistas = true;
                while (agregarRevistas) {
                    System.out.print("Ingrese el título de la revista: ");
                    String tituloRevista = scanner.nextLine();
                    System.out.print("Ingrese la fecha de publicación de la revista: ");
                    String fechaPublicacion = scanner.nextLine();

                    // Crear un objeto Revista y agregarlo a la Sala de Recepción
                    Revista nuevaRevista = new Revista(tituloRevista, fechaPublicacion);
                    nuevaSalaRecepcion.agregarRevista(nuevaRevista);

                    System.out.print("¿Desea agregar otra revista? (s/n): ");
                    String respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("n")) {
                        agregarRevistas = false;
                    }
                }

                // Guardar la Sala de Recepción y sus Revistas en la base de datos
                nuevaSalaRecepcion.guardarEnBaseDatos(connection);
                break;
                
                
            case 45: 
                // Llenar datos para el Centro de Atención Urgente
                System.out.print("Ingrese el nombre del centro de atención urgente: ");
                String nombreCentroAtencion = scanner.nextLine();

                // Crear un objeto Centro de Atención Urgente
                CentroAtencionUrgente nuevoCentroAtencion = new CentroAtencionUrgente(nombreCentroAtencion);

                // Llenar datos para los Protocolos de Atención
                boolean agregarProtocolos = true;
                while (agregarProtocolos) {
                    System.out.print("Ingrese el nombre del protocolo de atención: ");
                    String nombreProtocolo = scanner.nextLine();
                    System.out.print("Ingrese la descripción del protocolo: ");
                    String descripcionProtocolo = scanner.nextLine();

                    // Crear un objeto Protocolo de Atención y agregarlo al Centro de Atención Urgente
                    ProtocoloAtencion nuevoProtocolo = new ProtocoloAtencion(nombreProtocolo, descripcionProtocolo);
                    nuevoCentroAtencion.agregarProtocolo(nuevoProtocolo);

                    System.out.print("¿Desea agregar otro protocolo? (s/n): ");
                    String respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("n")) {
                        agregarProtocolos = false;
                    }
                }

                // Guardar el Centro de Atención Urgente y sus Protocolos en la base de datos
                nuevoCentroAtencion.guardarEnBaseDatos(connection);
                break;

                
                case 46: 
                // Llenar datos para el Área de Observación
                System.out.print("Ingrese el nombre del área de observación: ");
                String nombreAreaObservacion = scanner.nextLine();

                // Crear un objeto Área de Observación
                AreaObservacion nuevaAreaObservacion = new AreaObservacion(nombreAreaObservacion);

                // Llenar datos para los Equipos de Monitoreo
                boolean agregarEquipos = true;
                while (agregarEquipos) {
                    System.out.print("Ingrese el nombre del equipo de monitoreo: ");
                    String nombreEquipo = scanner.nextLine();
                    System.out.print("Ingrese el tipo de equipo de monitoreo: ");
                    String tipoDelEquipo = scanner.nextLine(); // Cambié el nombre de tipoEquipo a tipoDelEquipo

                    // Crear un objeto Equipo de Monitoreo y agregarlo al Área de Observación
                    EquipoMonitoreo nuevoEquipo = new EquipoMonitoreo(nombreEquipo, tipoDelEquipo);
                    nuevaAreaObservacion.agregarEquipo(nuevoEquipo);

                    System.out.print("¿Desea agregar otro equipo? (s/n): ");
                    String respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("n")) {
                        agregarEquipos = false;
                    }
                }

                // Guardar el Área de Observación y sus Equipos en la base de datos
                nuevaAreaObservacion.guardarEnBaseDatos(connection);
                break;
                
               
    
            case 47: 
            // Ingresar datos para los Exámenes Médicos
            System.out.println("Seleccione el tipo de examen a realizar:");
            System.out.println("1. Examen de Sangre");
            System.out.println("2. Examen de Orina");
            System.out.println("3. Examen Radiológico");
            System.out.println("4. Salir");
            int opcionExamen = scanner.nextInt();  // Cambié 'opcion' a 'opcionExamen'
            scanner.nextLine(); // Consumir nueva línea

            if (opcionExamen == 4) {
                break;
            }

            System.out.print("Ingrese el nombre del examen: ");
            String nombreExamen = scanner.nextLine();
            System.out.print("Ingrese el precio del examen: ");
            double precioExamen = scanner.nextDouble();
            scanner.nextLine(); // Consumir nueva línea

            Examen examen = null;
            switch (opcionExamen) {  // Cambié 'opcion' a 'opcionExamen'
                case 1:
                    examen = new ExamenSangre(nombreExamen, precioExamen);
                    break;
                case 2:
                    examen = new ExamenOrina(nombreExamen, precioExamen);
                    break;
                case 3:
                    examen = new ExamenRadiologico(nombreExamen, precioExamen);
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }

            if (examen != null) {
                // Realizar el examen
                examen.realizarExamen();

                // Guardar el examen en la base de datos
                examen.guardarEnBaseDatos(connection);
            }
            break;
            
            
            
            case 48: 
                // Ingresar datos para los Tratamientos Médicos
                System.out.println("Seleccione el tipo de tratamiento a aplicar:");
                System.out.println("1. Terapia Física");
                System.out.println("2. Quimioterapia");
                System.out.println("3. Radioterapia");
                System.out.println("4. Salir");
                int opcionTratamiento = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea

                if (opcionTratamiento == 4) {
                    break;
                }

                System.out.print("Ingrese el nombre del tratamiento: ");
                String nombreTratamiento = scanner.nextLine();
                System.out.print("Ingrese la duración en días del tratamiento: ");
                int duracionDiasTratamiento = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea

                Tratamiento tratamiento = null;
                switch (opcionTratamiento) {
                    case 1:
                        tratamiento = new TerapiaFisica(nombreTratamiento, duracionDiasTratamiento);
                        break;
                    case 2:
                        tratamiento = new Quimioterapia(nombreTratamiento, duracionDiasTratamiento);
                        break;
                    case 3:
                        tratamiento = new Radioterapia(nombreTratamiento, duracionDiasTratamiento);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }

                if (tratamiento != null) {
                    // Aplicar el tratamiento
                    tratamiento.aplicarTratamiento();

                    // Guardar el tratamiento en la base de datos
                    tratamiento.guardarEnBaseDatos(connection);
                }
                break;
                
               
                case 49:  
                    
                    System.out.println("Seleccione el tipo de cama:");
                    System.out.println("1. Cama UCI");
                    System.out.println("2. Cama Quirúrgica");
                    System.out.println("3. Cama Pediátrica");
                    System.out.println("4. Salir");
                    int opcioncama = scanner.nextInt();
                    scanner.nextLine(); // Consumir nueva línea

                    if (opcioncama == 4) {
                        break;
                    }

                    System.out.print("Ingrese la ubicación de la cama: ");
                    String ubicacion = scanner.nextLine();

                    Cama cama = null;
                    switch (opcioncama) { // Cambié 'opcion' por 'opcioncama'
                        case 1:
                            cama = new CamaUCI(ubicacion);
                            break;
                        case 2:
                            cama = new CamaQuirurgica(ubicacion);
                            break;
                        case 3:
                            cama = new CamaPediatrica(ubicacion);
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }

                    if (cama != null) {
                        // Guardar la cama en la base de datos
                        cama.guardarEnBaseDatos(connection);
                        System.out.println("Cama registrada exitosamente en la base de datos.");
                    }
                break;
                
                case 50:
                System.out.println("Seleccione el tipo de procedimiento:");
                System.out.println("1. Procedimiento Cardiológico");
                System.out.println("2. Procedimiento Radiológico");
                System.out.println("3. Procedimiento Gastroenterológico");
                System.out.println("4. Salir");
                int opcionProcedimiento = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea

                if (opcionProcedimiento == 4) {
                    break;
                }

                System.out.print("Ingrese la duración del procedimiento en minutos: ");
                int duracion = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea

                ProcedimientoMedico procedimiento = null;

                switch (opcionProcedimiento) {
                    case 1:
                        procedimiento = new ProcedimientoCardiologico(duracion);
                        break;
                    case 2:
                        procedimiento = new ProcedimientoRadiologico(duracion);
                        break;
                    case 3:
                        procedimiento = new ProcedimientoGastroenterologico(duracion);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }

                if (procedimiento != null) {
                    // Guardar el procedimiento en la base de datos
                    procedimiento.guardarEnBaseDatos(connection);
                    System.out.println("Procedimiento registrado exitosamente en la base de datos.");
                }
                break;


                
                case 51:
                    System.out.println("Seleccione el tipo de receta:");
                    System.out.println("1. Receta Pediátrica");
                    System.out.println("2. Receta Geriátrica");
                    System.out.println("3. Receta Oncológica");
                    System.out.println("4. Salir");
                    int opcionReceta = scanner.nextInt();
                    scanner.nextLine(); // Consumir nueva línea

                    if (opcionReceta == 4) {
                        break;
                    }

                    System.out.print("Ingrese el nombre del medicamento: ");
                    String medicamento = scanner.nextLine();

                    RecetaMedica receta = null;

                    switch (opcionReceta) {
                        case 1:
                            receta = new RecetaPediatrica(medicamento);
                            break;
                        case 2:
                            receta = new RecetaGeriatrica(medicamento);
                            break;
                        case 3:
                            receta = new RecetaOncologica(medicamento);
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }

                    if (receta != null) {
                        // Guardar la receta en la base de datos
                        receta.guardarEnBaseDatos(connection);
                        System.out.println("Receta registrada exitosamente en la base de datos.");
                    }
                    break;
                

       
                    
            }

        } catch (SQLException e) {
            System.out.println("No se pudo conectar a la base de datos.");
            e.printStackTrace();
        }
        
        

        
    }

    
    // Métodos para capturar los datos y hacer las inserciones para cada tipo de entidad

    private static void obtenerDatosPacienteYDoctor(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==== Ingreso de datos del Doctor ====");
        System.out.print("Ingrese la cédula del Doctor: ");
        String cedulaDoctor = scanner.nextLine();

        System.out.print("Ingrese el nombre del Doctor: ");
        String nombreDoctor = scanner.nextLine();

        System.out.print("Ingrese la especialidad del Doctor: ");
        String especialidad = scanner.nextLine();

        System.out.print("Ingrese el correo del Doctor: ");
        String correoDoctor = scanner.nextLine();

        // Insertar datos del doctor en la base de datos
        int doctorId = insertarDoctor(connection, cedulaDoctor, nombreDoctor, especialidad, correoDoctor);

        // Ingreso de datos del Paciente
            System.out.println("==== Ingreso de datos del Paciente ====");
            System.out.print("Ingrese la cédula del Paciente: ");
            String cedulaPaciente = scanner.nextLine();

            System.out.print("Ingrese el nombre del Paciente: ");
            String nombrePaciente = scanner.nextLine();

            System.out.print("Ingrese el apellido del Paciente: ");
            String apellidoPaciente = scanner.nextLine();

            System.out.print("Ingrese la edad del Paciente: ");
            int edadPaciente = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            System.out.print("Ingrese la dirección del Paciente: ");
            String direccionPaciente = scanner.nextLine();

            System.out.print("Ingrese el correo del Paciente: ");
            String correoPaciente = scanner.nextLine();

            System.out.print("Ingrese la enfermedad del Paciente: ");
            String enfermedad = scanner.nextLine();

            System.out.print("Ingrese el pronóstico del Paciente: ");
            String pronostico = scanner.nextLine();
            
            // Insertar datos del paciente en la base de datos, asociado con el ID del doctor
            insertarPaciente(connection, cedulaPaciente, nombrePaciente, apellidoPaciente, edadPaciente, direccionPaciente, correoPaciente, enfermedad, pronostico, doctorId);
            System.out.println("Proceso completado. ¡Paciente y Doctor registrados exitosamente!");

       
    }

    private static int insertarDoctor(Connection connection, String cedula, String nombre, String especialidad, String correo) {
        String insertDoctorQuery = "INSERT INTO doctor (cedula, nombre, especialidad, correo) VALUES (?, ?, ?, ?) RETURNING id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertDoctorQuery)) {
            preparedStatement.setString(1, cedula);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, especialidad);
            preparedStatement.setString(4, correo);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el doctor.");
            e.printStackTrace();
        }
        return -1;
    }
        
                private static void insertarPaciente(Connection connection, String cedula, String nombre, String apellido, int edad, String direccion, String correo, String enfermedad, String pronostico, int doctorId) {
            String insertPacienteQuery = "INSERT INTO paciente (cedula, nombre, apellido, edad, direccion, correo, enfermedad, pronostico, doctor_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertPacienteQuery)) {
                preparedStatement.setString(1, cedula);
                preparedStatement.setString(2, nombre);
                preparedStatement.setString(3, apellido);
                preparedStatement.setInt(4, edad);
                preparedStatement.setString(5, direccion);
                preparedStatement.setString(6, correo);
                preparedStatement.setString(7, enfermedad);
                preparedStatement.setString(8, pronostico);
                preparedStatement.setInt(9, doctorId);

                preparedStatement.executeUpdate();
                System.out.println("Datos del paciente insertados exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al insertar el paciente.");
                e.printStackTrace();
            }
        }

             private static PersonalAseo obtenerDatosPersonalAseo() {
                    Scanner scanner = new Scanner(System.in);

                    System.out.print("Ingrese el ID: "); // Nueva entrada para el ID
                    int id = Integer.parseInt(scanner.nextLine()); // Convierte la entrada a entero

                    System.out.print("Ingrese la cédula: ");
                    String cedula = scanner.nextLine();

                    System.out.print("Ingrese la hora: ");
                    String hora = scanner.nextLine();

                    System.out.print("Ingrese el tiempo trabajado: ");
                    String tiempotrabajado = scanner.nextLine();

                    System.out.print("Ingrese el nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Ingrese el área: ");
                    String area = scanner.nextLine();

                    System.out.print("Ingrese el correo: ");
                    String correo = scanner.nextLine();

                    return new PersonalAseo(id, hora, tiempotrabajado, cedula, nombre, area, correo); // Incluye el ID
                }


    private static void insertarPersonalAseo(Connection connection, PersonalAseo personal) {
        String insertPersonalAseoQuery = "INSERT INTO personal_aseo (hora, tiempotrabajado, cedula, nombre, area, correo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertPersonalAseoQuery)) {
            preparedStatement.setString(1, personal.getHora());
            preparedStatement.setString(2, personal.getTiempotrabajado());
            preparedStatement.setString(3, personal.getCedula());
            preparedStatement.setString(4, personal.getNombre());
            preparedStatement.setString(5, personal.getArea());
            preparedStatement.setString(6, personal.getCorreo());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar personal de aseo.");
            e.printStackTrace();
        }
    }
    
    private static Guardiaseguridad obtenerDatosGuardiaseguridad() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Ingrese la turno de la semana: ");
    String turnossemana = scanner.nextLine();

    System.out.print("Ingrese la hora de tiempo que lleva trabajado: ");
    String tiempotrabajado = scanner.nextLine();

    System.out.print("Ingrese la hora de inicio del turno: ");
    String horainicio = scanner.nextLine();

    System.out.print("Ingrese la hora de finalizar el turno: ");
    String horafin = scanner.nextLine();

    System.out.print("Ingrese la cedula: ");
    String cedula = scanner.nextLine();

    System.out.print("Ingrese el nombre: ");
    String nombre = scanner.nextLine();

    System.out.print("Ingrese el apellido: ");
    String apellido = scanner.nextLine();

    System.out.print("Ingrese el correo: ");
    String correo = scanner.nextLine();

    return new Guardiaseguridad(turnossemana, tiempotrabajado, horainicio, horafin, cedula, nombre, apellido, correo);
}

private static void insertarGuardiaseguridad(Connection connection, Guardiaseguridad guardia) {
    // Actualiza la consulta para incluir todos los campos necesarios
    String insertGuardiaseguridadQuery = "INSERT INTO guardiaseguridad (horainicio, horafin, cedula, nombre, apellido, correo, turnossemana, tiempotrabajado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(insertGuardiaseguridadQuery)) {
        preparedStatement.setString(1, guardia.getHorainicio()); // horainicio
        preparedStatement.setString(2, guardia.getHorafin()); // horafin
        preparedStatement.setString(3, guardia.getCedula()); // cedula
        preparedStatement.setString(4, guardia.getNombre()); // nombre
        preparedStatement.setString(5, guardia.getApellido()); // apellido
        preparedStatement.setString(6, guardia.getCorreo()); // correo
        preparedStatement.setString(7, guardia.getTurnossemana()); // turnossemana
        preparedStatement.setString(8, guardia.getTiempotrabajado()); // tiempotrabajado

        preparedStatement.executeUpdate();
        System.out.println("Guardia de Seguridad registrado exitosamente!");
    } catch (SQLException e) {
        System.out.println("Error al insertar guardia de seguridad.");
        e.printStackTrace();
    }
}

    
    private static Cocinero obtenerDatosCocinero() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la turno de la semana: ");
        String turnossemana = scanner.nextLine();

        System.out.print("Ingrese la hora de tiempo que lleva trabajado: ");
        String tiempotrabajado = scanner.nextLine();

        System.out.print("Ingrese la hora de inico del turno: ");
        String horainicio = scanner.nextLine();

        System.out.print("Ingrese la hora de finalizar el turno: ");
        String horafin = scanner.nextLine();

        System.out.print("Ingrese el cedula: ");
        String cedula = scanner.nextLine();

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
                
        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine();
        
        System.out.print("Ingrese el correo: ");
        String correo = scanner.nextLine();
  

        return new Cocinero(turnossemana, tiempotrabajado, horainicio, horafin, cedula, nombre, apellido, correo );
    }

        private static void insertarCocinero(Connection connection, Cocinero cocinero) {
            String insertCocineroQuery = "INSERT INTO cocinero (horainicio, horafin, cedula, nombre, apellido, correo, turnossemana, tiempotrabajado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
         try (PreparedStatement preparedStatement = connection.prepareStatement(insertCocineroQuery)) {
            preparedStatement.setString(1, cocinero.getHorainicio());
            preparedStatement.setString(2, cocinero.getHorafin());
            preparedStatement.setString(3, cocinero.getCedula());
            preparedStatement.setString(4, cocinero.getNombre());
            preparedStatement.setString(5, cocinero.getApellido());
            preparedStatement.setString(6, cocinero.getCorreo());
            preparedStatement.setString(7, cocinero.getTurnossemana());  // Insertar turnossemana
            preparedStatement.setString(8, cocinero.getTiempotrabajado());  // Insertar tiempotrabajado

             preparedStatement.executeUpdate();
         } catch (SQLException e) {
             System.out.println("Error al insertar cocinero.");
             e.printStackTrace();
         }
     }

        
    private static Conductorambulancia obtenerDatosConductorambulancia() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la turno de la semana: ");
        String turnossemana = scanner.nextLine();

        System.out.print("Ingrese la hora de tiempo que lleva trabajado: ");
        String tiempotrabajado = scanner.nextLine();

        System.out.print("Ingrese la hora de inico del turno: ");
        String horainicio = scanner.nextLine();

        System.out.print("Ingrese la hora de finalizar el turno: ");
        String horafin = scanner.nextLine();

        System.out.print("Ingrese el cedula: ");
        String cedula = scanner.nextLine();

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
                
        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine();
        
        System.out.print("Ingrese el correo: ");
        String correo = scanner.nextLine();
  

        return new Conductorambulancia (turnossemana, tiempotrabajado, horainicio, horafin, cedula, nombre, apellido, correo );
    }

        private static void insertarConductorambulancia(Connection connection, Conductorambulancia ambulancia) {
            String insertConductorambulanciaQuery = "INSERT INTO conductorambulancia (horainicio, horafin, cedula, nombre, apellido, correo, turnossemana, tiempotrabajado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertConductorambulanciaQuery)) {
                preparedStatement.setString(1, ambulancia.getHorainicio());
                preparedStatement.setString(2, ambulancia.getHorafin());
                preparedStatement.setString(3, ambulancia.getCedula());
                preparedStatement.setString(4, ambulancia.getNombre());
                preparedStatement.setString(5, ambulancia.getApellido());
                preparedStatement.setString(6, ambulancia.getCorreo());
                preparedStatement.setString(7, ambulancia.getTurnossemana());  // Insertar turnossemana
                preparedStatement.setString(8, ambulancia.getTiempotrabajado());  // Insertar tiempotrabajado

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al insertar conductor de ambulancia.");
                e.printStackTrace();
            }
        }
        
    private static Personalmantenimiento obtenerDatosPersonalmantenimiento() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la turno de la semana: ");
        String turnossemana = scanner.nextLine();

        System.out.print("Ingrese la hora de tiempo que lleva trabajado: ");
        String tiempotrabajado = scanner.nextLine();

        System.out.print("Ingrese la hora de inico del turno: ");
        String horainicio = scanner.nextLine();

        System.out.print("Ingrese la hora de finalizar el turno: ");
        String horafin = scanner.nextLine();

        System.out.print("Ingrese el cedula: ");
        String cedula = scanner.nextLine();

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
                
        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine();
        
        System.out.print("Ingrese el correo: ");
        String correo = scanner.nextLine();
  

        return new Personalmantenimiento (turnossemana, tiempotrabajado, horainicio, horafin, cedula, nombre, apellido, correo );
    }

        private static void insertarPersonalmantenimiento(Connection connection, Personalmantenimiento mantenimiento) {
            String insertPersonalmantenimientoQuery = "INSERT INTO personalmantenimiento (horainicio, horafin, cedula, nombre, apellido, correo, turnossemana, tiempotrabajado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertPersonalmantenimientoQuery)) {
                preparedStatement.setString(1, mantenimiento.getHorainicio());
                preparedStatement.setString(2, mantenimiento.getHorafin());
                preparedStatement.setString(3, mantenimiento.getCedula());
                preparedStatement.setString(4, mantenimiento.getNombre());
                preparedStatement.setString(5, mantenimiento.getApellido());
                preparedStatement.setString(6, mantenimiento.getCorreo());
                preparedStatement.setString(7, mantenimiento.getTurnossemana());  // Insertar turnossemana
                preparedStatement.setString(8, mantenimiento.getTiempotrabajado());  // Insertar tiempotrabajado

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al insertar conductor de ambulancia.");
                e.printStackTrace();
            }
        }
        
            private static Personalportero obtenerDatosPersonalportero() {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Ingrese la turno de la semana: ");
                String turnossemana = scanner.nextLine();

                System.out.print("Ingrese la hora de tiempo que lleva trabajado: ");
                String tiempotrabajado = scanner.nextLine();

                System.out.print("Ingrese la hora de inico del turno: ");
                String horainicio = scanner.nextLine();

                System.out.print("Ingrese la hora de finalizar el turno: ");
                String horafin = scanner.nextLine();

                System.out.print("Ingrese el cedula: ");
                String cedula = scanner.nextLine();

                System.out.print("Ingrese el nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese el apellido: ");
                String apellido = scanner.nextLine();

                System.out.print("Ingrese el correo: ");
                String correo = scanner.nextLine();


        return new Personalportero (turnossemana, tiempotrabajado, horainicio, horafin, cedula, nombre, apellido, correo );
    }

        private static void insertarPersonalportero(Connection connection, Personalportero portero) {
            String insertPersonalporteroQuery = "INSERT INTO Personalportero (horainicio, horafin, cedula, nombre, apellido, correo, turnossemana, tiempotrabajado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertPersonalporteroQuery)) {
                preparedStatement.setString(1, portero.getHorainicio());
                preparedStatement.setString(2, portero.getHorafin());
                preparedStatement.setString(3, portero.getCedula());
                preparedStatement.setString(4, portero.getNombre());
                preparedStatement.setString(5, portero.getApellido());
                preparedStatement.setString(6, portero.getCorreo());
                preparedStatement.setString(7, portero.getTurnossemana());  
                preparedStatement.setString(8, portero.getTiempotrabajado()); 

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al insertar conductor de ambulancia.");
                e.printStackTrace();
            }
        }
        
            private static Jefedeplanta obtenerDatosJefedeplanta() {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Ingrese la turno de la semana: ");
                String turnossemana = scanner.nextLine();

                System.out.print("Ingrese la hora de tiempo que lleva trabajado: ");
                String tiempotrabajado = scanner.nextLine();

                System.out.print("Ingrese la hora de inico del turno: ");
                String horainicio = scanner.nextLine();

                System.out.print("Ingrese la hora de finalizar el turno: ");
                String horafin = scanner.nextLine();

                System.out.print("Ingrese el cedula: ");
                String cedula = scanner.nextLine();

                System.out.print("Ingrese el nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese el apellido: ");
                String apellido = scanner.nextLine();

                System.out.print("Ingrese el correo: ");
                String correo = scanner.nextLine();


        return new Jefedeplanta (turnossemana, tiempotrabajado, horainicio, horafin, cedula, nombre, apellido, correo );
    }

        private static void insertarJefedeplanta(Connection connection, Jefedeplanta planta) {
            String insertJefedeplantaQuery = "INSERT INTO Jefedeplanta (horainicio, horafin, cedula, nombre, apellido, correo, turnossemana, tiempotrabajado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertJefedeplantaQuery)) {
                preparedStatement.setString(1, planta.getHorainicio());
                preparedStatement.setString(2, planta.getHorafin());
                preparedStatement.setString(3, planta.getCedula());
                preparedStatement.setString(4, planta.getNombre());
                preparedStatement.setString(5, planta.getApellido());
                preparedStatement.setString(6, planta.getCorreo());
                preparedStatement.setString(7, planta.getTurnossemana());  
                preparedStatement.setString(8, planta.getTiempotrabajado()); 

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al insertar conductor de ambulancia.");
                e.printStackTrace();
            }
        }

            private static Personalarchivo obtenerDatosPersonalarchivo() {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Ingrese la turno de la semana: ");
                String turnossemana = scanner.nextLine();

                System.out.print("Ingrese la hora de tiempo que lleva trabajado: ");
                String tiempotrabajado = scanner.nextLine();

                System.out.print("Ingrese la hora de inico del turno: ");
                String horainicio = scanner.nextLine();

                System.out.print("Ingrese la hora de finalizar el turno: ");
                String horafin = scanner.nextLine();

                System.out.print("Ingrese el cedula: ");
                String cedula = scanner.nextLine();

                System.out.print("Ingrese el nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese el apellido: ");
                String apellido = scanner.nextLine();

                System.out.print("Ingrese el correo: ");
                String correo = scanner.nextLine();


        return new Personalarchivo (turnossemana, tiempotrabajado, horainicio, horafin, cedula, nombre, apellido, correo );
    }

        private static void insertarPersonalarchivo(Connection connection, Personalarchivo archivo) {
            String insertPersonalarchivoQuery = "INSERT INTO Personalarchivo (horainicio, horafin, cedula, nombre, apellido, correo, turnossemana, tiempotrabajado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertPersonalarchivoQuery)) {
                preparedStatement.setString(1, archivo.getHorainicio());
                preparedStatement.setString(2, archivo.getHorafin());
                preparedStatement.setString(3, archivo.getCedula());
                preparedStatement.setString(4, archivo.getNombre());
                preparedStatement.setString(5, archivo.getApellido());
                preparedStatement.setString(6, archivo.getCorreo());
                preparedStatement.setString(7, archivo.getTurnossemana());  
                preparedStatement.setString(8, archivo.getTiempotrabajado()); 

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al insertar conductor de ambulancia.");
                e.printStackTrace();
            }
        }

            private static Operadorcentral obtenerDatosOperadorcentral() {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Ingrese la turno de la semana: ");
                String turnossemana = scanner.nextLine();

                System.out.print("Ingrese la hora de tiempo que lleva trabajado: ");
                String tiempotrabajado = scanner.nextLine();

                System.out.print("Ingrese la hora de inico del turno: ");
                String horainicio = scanner.nextLine();

                System.out.print("Ingrese la hora de finalizar el turno: ");
                String horafin = scanner.nextLine();

                System.out.print("Ingrese el cedula: ");
                String cedula = scanner.nextLine();

                System.out.print("Ingrese el nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese el apellido: ");
                String apellido = scanner.nextLine();

                System.out.print("Ingrese el correo: ");
                String correo = scanner.nextLine();


        return new Operadorcentral (turnossemana, tiempotrabajado, horainicio, horafin, cedula, nombre, apellido, correo );
    }

        private static void insertarOperadorcentral(Connection connection, Operadorcentral central) {
            String insertOperadorcentralQuery = "INSERT INTO Operadorcentral (horainicio, horafin, cedula, nombre, apellido, correo, turnossemana, tiempotrabajado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertOperadorcentralQuery)) {
                preparedStatement.setString(1, central.getHorainicio());
                preparedStatement.setString(2, central.getHorafin());
                preparedStatement.setString(3, central.getCedula());
                preparedStatement.setString(4, central.getNombre());
                preparedStatement.setString(5, central.getApellido());
                preparedStatement.setString(6, central.getCorreo());
                preparedStatement.setString(7, central.getTurnossemana());  
                preparedStatement.setString(8, central.getTiempotrabajado()); 

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al insertar conductor de ambulancia.");
                e.printStackTrace();
            }
        }
            private static Supervisoraseo obtenerDatosSupervisoraseo() {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Ingrese la turno de la semana: ");
                String turnossemana = scanner.nextLine();

                System.out.print("Ingrese la hora de tiempo que lleva trabajado: ");
                String tiempotrabajado = scanner.nextLine();

                System.out.print("Ingrese la hora de inico del turno: ");
                String horainicio = scanner.nextLine();

                System.out.print("Ingrese la hora de finalizar el turno: ");
                String horafin = scanner.nextLine();

                System.out.print("Ingrese el cedula: ");
                String cedula = scanner.nextLine();

                System.out.print("Ingrese el nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese el apellido: ");
                String apellido = scanner.nextLine();

                System.out.print("Ingrese el correo: ");
                String correo = scanner.nextLine();
                
        return new Supervisoraseo (turnossemana, tiempotrabajado, horainicio, horafin, cedula, nombre, apellido, correo );
    }

        private static void insertarSupervisoraseo(Connection connection, Supervisoraseo soraseo) {
            String insertSupervisoraseoQuery = "INSERT INTO Supervisoraseo (horainicio, horafin, cedula, nombre, apellido, correo, turnossemana, tiempotrabajado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSupervisoraseoQuery)) {
                preparedStatement.setString(1, soraseo.getHorainicio());
                preparedStatement.setString(2, soraseo.getHorafin());
                preparedStatement.setString(3, soraseo.getCedula());
                preparedStatement.setString(4, soraseo.getNombre());
                preparedStatement.setString(5, soraseo.getApellido());
                preparedStatement.setString(6, soraseo.getCorreo());
                preparedStatement.setString(7, soraseo.getTurnossemana());  
                preparedStatement.setString(8, soraseo.getTiempotrabajado()); 

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al insertar conductor de ambulancia.");
                e.printStackTrace();
            }
        }


            private static Secretario obtenerDatosSecretario() {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Ingrese la turno de la semana: ");
                String turnossemana = scanner.nextLine();

                System.out.print("Ingrese la hora de tiempo que lleva trabajado: ");
                String tiempotrabajado = scanner.nextLine();

                System.out.print("Ingrese la hora de inico del turno: ");
                String horainicio = scanner.nextLine();

                System.out.print("Ingrese la hora de finalizar el turno: ");
                String horafin = scanner.nextLine();

                System.out.print("Ingrese el cedula: ");
                String cedula = scanner.nextLine();

                System.out.print("Ingrese el nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese el apellido: ");
                String apellido = scanner.nextLine();

                System.out.print("Ingrese el correo: ");
                String correo = scanner.nextLine();
                
        return new Secretario (turnossemana, tiempotrabajado, horainicio, horafin, cedula, nombre, apellido, correo );
    }

        private static void insertarSecretario(Connection connection, Secretario cretario) {
            String insertSecretarioQuery = "INSERT INTO Supervisoraseo (horainicio, horafin, cedula, nombre, apellido, correo, turnossemana, tiempotrabajado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSecretarioQuery)) {
                preparedStatement.setString(1, cretario.getHorainicio());
                preparedStatement.setString(2, cretario.getHorafin());
                preparedStatement.setString(3, cretario.getCedula());
                preparedStatement.setString(4, cretario.getNombre());
                preparedStatement.setString(5, cretario.getApellido());
                preparedStatement.setString(6, cretario.getCorreo());
                preparedStatement.setString(7, cretario.getTurnossemana());  
                preparedStatement.setString(8, cretario.getTiempotrabajado()); 

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al insertar conductor de ambulancia.");
                e.printStackTrace();
            }
        }
        
            private static Cajero obtenerDatosCajero() {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Ingrese la turno de la semana: ");
                String turnossemana = scanner.nextLine();

                System.out.print("Ingrese la hora de tiempo que lleva trabajado: ");
                String tiempotrabajado = scanner.nextLine();

                System.out.print("Ingrese la hora de inico del turno: ");
                String horainicio = scanner.nextLine();

                System.out.print("Ingrese la hora de finalizar el turno: ");
                String horafin = scanner.nextLine();

                System.out.print("Ingrese el cedula: ");
                String cedula = scanner.nextLine();

                System.out.print("Ingrese el nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese el apellido: ");
                String apellido = scanner.nextLine();

                System.out.print("Ingrese el correo: ");
                String correo = scanner.nextLine();
                
        return new Cajero (turnossemana, tiempotrabajado, horainicio, horafin, cedula, nombre, apellido, correo );
    }

        private static void insertarCajero(Connection connection, Cajero jero) {
            String insertCajeroQuery = "INSERT INTO Cajero (horainicio, horafin, cedula, nombre, apellido, correo, turnossemana, tiempotrabajado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertCajeroQuery)) {
                preparedStatement.setString(1, jero.getHorainicio());
                preparedStatement.setString(2, jero.getHorafin());
                preparedStatement.setString(3, jero.getCedula());
                preparedStatement.setString(4, jero.getNombre());
                preparedStatement.setString(5, jero.getApellido());
                preparedStatement.setString(6, jero.getCorreo());
                preparedStatement.setString(7, jero.getTurnossemana());  
                preparedStatement.setString(8, jero.getTiempotrabajado()); 

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al insertar conductor de ambulancia.");
                e.printStackTrace();
            }
        }
            private static Encargadofarmacia obtenerDatosEncargadofarmacia() {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Ingrese la turno de la semana: ");
                String turnossemana = scanner.nextLine();

                System.out.print("Ingrese la hora de tiempo que lleva trabajado: ");
                String tiempotrabajado = scanner.nextLine();

                System.out.print("Ingrese la hora de inico del turno: ");
                String horainicio = scanner.nextLine();

                System.out.print("Ingrese la hora de finalizar el turno: ");
                String horafin = scanner.nextLine();

                System.out.print("Ingrese el cedula: ");
                String cedula = scanner.nextLine();

                System.out.print("Ingrese el nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese el apellido: ");
                String apellido = scanner.nextLine();

                System.out.print("Ingrese el correo: ");
                String correo = scanner.nextLine();
                
        return new Encargadofarmacia (turnossemana, tiempotrabajado, horainicio, horafin, cedula, nombre, apellido, correo );
    }

        private static void insertarEncargadofarmacia(Connection connection, Encargadofarmacia farmacia) {
            String insertEncargadofarmaciaQuery = "INSERT INTO Encargadofarmacia (horainicio, horafin, cedula, nombre, apellido, correo, turnossemana, tiempotrabajado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertEncargadofarmaciaQuery)) {
                preparedStatement.setString(1, farmacia.getHorainicio());
                preparedStatement.setString(2, farmacia.getHorafin());
                preparedStatement.setString(3, farmacia.getCedula());
                preparedStatement.setString(4, farmacia.getNombre());
                preparedStatement.setString(5, farmacia.getApellido());
                preparedStatement.setString(6, farmacia.getCorreo());
                preparedStatement.setString(7, farmacia.getTurnossemana());  
                preparedStatement.setString(8, farmacia.getTiempotrabajado()); 

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al insertar conductor de ambulancia.");
                e.printStackTrace();
            }
        }
        
            private static Voluntario obtenerDatosVoluntario() {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Ingrese la turno de la semana: ");
                String turnossemana = scanner.nextLine();

                System.out.print("Ingrese la hora de tiempo que lleva trabajado: ");
                String tiempotrabajado = scanner.nextLine();

                System.out.print("Ingrese la hora de inico del turno: ");
                String horainicio = scanner.nextLine();

                System.out.print("Ingrese la hora de finalizar el turno: ");
                String horafin = scanner.nextLine();

                System.out.print("Ingrese el cedula: ");
                String cedula = scanner.nextLine();

                System.out.print("Ingrese el nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese el apellido: ");
                String apellido = scanner.nextLine();

                System.out.print("Ingrese el correo: ");
                String correo = scanner.nextLine();
                
        return new Voluntario (turnossemana, tiempotrabajado, horainicio, horafin, cedula, nombre, apellido, correo );
    }

        private static void insertarVoluntario(Connection connection, Voluntario tario) {
            String insertEncargadofarmaciaQuery = "INSERT INTO Encargadofarmacia (horainicio, horafin, cedula, nombre, apellido, correo, turnossemana, tiempotrabajado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertEncargadofarmaciaQuery)) {
                preparedStatement.setString(1, tario.getHorainicio());
                preparedStatement.setString(2, tario.getHorafin());
                preparedStatement.setString(3, tario.getCedula());
                preparedStatement.setString(4, tario.getNombre());
                preparedStatement.setString(5, tario.getApellido());
                preparedStatement.setString(6, tario.getCorreo());
                preparedStatement.setString(7, tario.getTurnossemana());  
                preparedStatement.setString(8, tario.getTiempotrabajado()); 

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al insertar conductor de ambulancia.");
                e.printStackTrace();
            }
        }
                
            private static Tecnicoradiologia obtenerDatosTecnicoradiologia() {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Ingrese la turno de la semana: ");
                String turnossemana = scanner.nextLine();

                System.out.print("Ingrese la hora de tiempo que lleva trabajado: ");
                String tiempotrabajado = scanner.nextLine();

                System.out.print("Ingrese la hora de inico del turno: ");
                String horainicio = scanner.nextLine();

                System.out.print("Ingrese la hora de finalizar el turno: ");
                String horafin = scanner.nextLine();

                System.out.print("Ingrese el cedula: ");
                String cedula = scanner.nextLine();

                System.out.print("Ingrese el nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese el apellido: ");
                String apellido = scanner.nextLine();

                System.out.print("Ingrese el correo: ");
                String correo = scanner.nextLine();
                
        return new Tecnicoradiologia (turnossemana, tiempotrabajado, horainicio, horafin, cedula, nombre, apellido, correo );
    }

        private static void insertarTecnicoradiologia(Connection connection, Tecnicoradiologia radiologia) {
            String insertTecnicoradiologiaQuery = "INSERT INTO Tecnicoradiologia (horainicio, horafin, cedula, nombre, apellido, correo, turnossemana, tiempotrabajado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertTecnicoradiologiaQuery)) {
                preparedStatement.setString(1, radiologia.getHorainicio());
                preparedStatement.setString(2, radiologia.getHorafin());
                preparedStatement.setString(3, radiologia.getCedula());
                preparedStatement.setString(4, radiologia.getNombre());
                preparedStatement.setString(5, radiologia.getApellido());
                preparedStatement.setString(6, radiologia.getCorreo());
                preparedStatement.setString(7, radiologia.getTurnossemana());  
                preparedStatement.setString(8, radiologia.getTiempotrabajado()); 

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al insertar conductor de ambulancia.");
                e.printStackTrace();
            }
        }
                
            private static Tecnicolaboratorio obtenerDatosTecnicolaboratorio() {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Ingrese la turno de la semana: ");
                String turnossemana = scanner.nextLine();

                System.out.print("Ingrese la hora de tiempo que lleva trabajado: ");
                String tiempotrabajado = scanner.nextLine();

                System.out.print("Ingrese la hora de inico del turno: ");
                String horainicio = scanner.nextLine();

                System.out.print("Ingrese la hora de finalizar el turno: ");
                String horafin = scanner.nextLine();

                System.out.print("Ingrese el cedula: ");
                String cedula = scanner.nextLine();

                System.out.print("Ingrese el nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese el apellido: ");
                String apellido = scanner.nextLine();

                System.out.print("Ingrese el correo: ");
                String correo = scanner.nextLine();
                
        return new Tecnicolaboratorio (turnossemana, tiempotrabajado, horainicio, horafin, cedula, nombre, apellido, correo );
    }

        private static void insertarTecnicolaboratorio(Connection connection, Tecnicolaboratorio laboratorio) {
            String insertTecnicolaboratorioQuery = "INSERT INTO Tecnicolaboratorio (horainicio, horafin, cedula, nombre, apellido, correo, turnossemana, tiempotrabajado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertTecnicolaboratorioQuery)) {
                preparedStatement.setString(1, laboratorio.getHorainicio());
                preparedStatement.setString(2, laboratorio.getHorafin());
                preparedStatement.setString(3, laboratorio.getCedula());
                preparedStatement.setString(4, laboratorio.getNombre());
                preparedStatement.setString(5, laboratorio.getApellido());
                preparedStatement.setString(6, laboratorio.getCorreo());
                preparedStatement.setString(7, laboratorio.getTurnossemana());  
                preparedStatement.setString(8, laboratorio.getTiempotrabajado()); 

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al insertar laboratorio.");
                e.printStackTrace();
            }
        }
                
            private static Fisioterapeuta obtenerDatosFisioterapeuta() {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Ingrese la turno de la semana: ");
                String turnossemana = scanner.nextLine();

                System.out.print("Ingrese la hora de tiempo que lleva trabajado: ");
                String tiempotrabajado = scanner.nextLine();

                System.out.print("Ingrese la hora de inico del turno: ");
                String horainicio = scanner.nextLine();

                System.out.print("Ingrese la hora de finalizar el turno: ");
                String horafin = scanner.nextLine();

                System.out.print("Ingrese el cedula: ");
                String cedula = scanner.nextLine();

                System.out.print("Ingrese el nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese el apellido: ");
                String apellido = scanner.nextLine();

                System.out.print("Ingrese el correo: ");
                String correo = scanner.nextLine();
                
        return new Fisioterapeuta (turnossemana, tiempotrabajado, horainicio, horafin, cedula, nombre, apellido, correo );
    }

        private static void insertarFisioterapeuta (Connection connection, Fisioterapeuta terapeuta) {
            String insertTecnicolaboratorioQuery = "INSERT INTO Tecnicolaboratorio (horainicio, horafin, cedula, nombre, apellido, correo, turnossemana, tiempotrabajado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertTecnicolaboratorioQuery)) {
                preparedStatement.setString(1, terapeuta.getHorainicio());
                preparedStatement.setString(2, terapeuta.getHorafin());
                preparedStatement.setString(3, terapeuta.getCedula());
                preparedStatement.setString(4, terapeuta.getNombre());
                preparedStatement.setString(5, terapeuta.getApellido());
                preparedStatement.setString(6, terapeuta.getCorreo());
                preparedStatement.setString(7, terapeuta.getTurnossemana());  
                preparedStatement.setString(8, terapeuta.getTiempotrabajado()); 

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al insertar laboratorio.");
                e.printStackTrace();
            }
        }
                
               
            private static Nutricionista obtenerDatosNutricionista() {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Ingrese la turno de la semana: ");
                String turnossemana = scanner.nextLine();

                System.out.print("Ingrese la hora de tiempo que lleva trabajado: ");
                String tiempotrabajado = scanner.nextLine();

                System.out.print("Ingrese la hora de inico del turno: ");
                String horainicio = scanner.nextLine();

                System.out.print("Ingrese la hora de finalizar el turno: ");
                String horafin = scanner.nextLine();

                System.out.print("Ingrese el cedula: ");
                String cedula = scanner.nextLine();

                System.out.print("Ingrese el nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese el apellido: ");
                String apellido = scanner.nextLine();

                System.out.print("Ingrese el correo: ");
                String correo = scanner.nextLine();
                
        return new Nutricionista (turnossemana, tiempotrabajado, horainicio, horafin, cedula, nombre, apellido, correo );
    }

        private static void insertarNutricionista (Connection connection, Nutricionista cionista) {
            String insertNutricionistaQuery = "INSERT INTO Nutricionista (horainicio, horafin, cedula, nombre, apellido, correo, turnossemana, tiempotrabajado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertNutricionistaQuery)) {
                preparedStatement.setString(1, cionista.getHorainicio());
                preparedStatement.setString(2, cionista.getHorafin());
                preparedStatement.setString(3, cionista.getCedula());
                preparedStatement.setString(4, cionista.getNombre());
                preparedStatement.setString(5, cionista.getApellido());
                preparedStatement.setString(6, cionista.getCorreo());
                preparedStatement.setString(7, cionista.getTurnossemana());  
                preparedStatement.setString(8, cionista.getTiempotrabajado()); 

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al insertar laboratorio.");
                e.printStackTrace();
            }
        }
                
               
            private static Psicologo obtenerDatosPsicologo() {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Ingrese la turno de la semana: ");
                String turnossemana = scanner.nextLine();

                System.out.print("Ingrese la hora de tiempo que lleva trabajado: ");
                String tiempotrabajado = scanner.nextLine();

                System.out.print("Ingrese la hora de inico del turno: ");
                String horainicio = scanner.nextLine();

                System.out.print("Ingrese la hora de finalizar el turno: ");
                String horafin = scanner.nextLine();

                System.out.print("Ingrese el cedula: ");
                String cedula = scanner.nextLine();

                System.out.print("Ingrese el nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese el apellido: ");
                String apellido = scanner.nextLine();

                System.out.print("Ingrese el correo: ");
                String correo = scanner.nextLine();
                
        return new Psicologo (turnossemana, tiempotrabajado, horainicio, horafin, cedula, nombre, apellido, correo );
    }

        private static void insertarPsicologo (Connection connection, Psicologo cologo) {
            String insertPsicologoQuery = "INSERT INTO Psicologo (horainicio, horafin, cedula, nombre, apellido, correo, turnossemana, tiempotrabajado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertPsicologoQuery)) {
                preparedStatement.setString(1, cologo.getHorainicio());
                preparedStatement.setString(2, cologo.getHorafin());
                preparedStatement.setString(3, cologo.getCedula());
                preparedStatement.setString(4, cologo.getNombre());
                preparedStatement.setString(5, cologo.getApellido());
                preparedStatement.setString(6, cologo.getCorreo());
                preparedStatement.setString(7, cologo.getTurnossemana());  
                preparedStatement.setString(8, cologo.getTiempotrabajado()); 

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al insertar laboratorio.");
                e.printStackTrace();
            }
        }
        
        
        
                
        
            
        
}

