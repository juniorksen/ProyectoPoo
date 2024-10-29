package com.mycompany.poomaven;

    abstract class Instructor {
    protected String nombre;
    protected String nivelEducativo;
    protected String especialidadDocente;
    protected int proyectosActivos;

    public Instructor(String nombre, String nivelEducativo, String especialidadDocente, int proyectosActivos) {
        this.nombre = nombre;
        this.nivelEducativo = nivelEducativo;
        this.especialidadDocente = especialidadDocente;
        this.proyectosActivos = proyectosActivos;
    }

    // Métodos getter para acceder a los atributos para las interfaz
    public String getNombre() {
        return nombre;
    }

    public String getNivelEducativo() {
        return nivelEducativo;
    }

    public String getEspecialidadDocente() {
        return especialidadDocente;
    }

    public int getProyectosActivos() {
        return proyectosActivos;
    }

    public abstract void planificarCurso();
    public abstract void evaluarEstudiantes();
    public abstract void publicarNotas();
}
