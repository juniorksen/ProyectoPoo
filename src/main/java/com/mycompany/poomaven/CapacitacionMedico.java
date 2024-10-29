package com.mycompany.poomaven;

import java.sql.Connection;

class CapacitacionMedico extends Capacitacion {
    private Connection connection;
    private String nivelEducativo;
    private String especialidadDocente;

    public CapacitacionMedico(String nombre, String campoInvestigacion, int proyectosActivos, 
                              String nivelEducativo, String especialidadDocente, Connection connection) {
        super(nombre, campoInvestigacion, proyectosActivos);
        this.nivelEducativo = nivelEducativo;
        this.especialidadDocente = especialidadDocente;
        this.connection = connection; // Inicializar el atributo connection
    }

    // Getters para nivelEducativo y especialidadDocente
    public String getNivelEducativo() {
        return nivelEducativo;
    }

    public String getEspecialidadDocente() {
        return especialidadDocente;
    }

    @Override
    public void organizarTaller() {
        System.out.println(getNombre() + " está organizando un taller en el campo de " + getCampoInvestigacion() + ".");
        registrarActividad("Organizar Taller", "Organizando un taller en el campo de " + getCampoInvestigacion());
    }

    @Override
    public void monitorearProgreso() {
        System.out.println(getNombre() + " está monitoreando el progreso de los proyectos.");
        registrarActividad("Monitorear Progreso", "Monitoreando el progreso de los proyectos en el campo de " + getCampoInvestigacion());
    }

    @Override
    public void InformeProgreso() {
        System.out.println(getNombre() + " está preparando un informe de progreso.");
        registrarActividad("Informe de Progreso", "Preparando un informe de progreso en el campo de " + getCampoInvestigacion());
    }

    // Método para registrar cada actividad en la base de datos
    private void registrarActividad(String actividad, String detalle) {
        // Aquí deberías implementar la lógica para registrar en la base de datos utilizando 'connection'
        System.out.println("Capacitación médica de " + getNombre() + " y actividad registrada: " + actividad);
    }
}
