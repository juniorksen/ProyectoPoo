package com.mycompany.poomaven;

public class Investigador {

    private String nombre;
    private String campoInvestigacion;
    private int proyectosActivos;

    public Investigador(String nombre, String campoInvestigacion, int proyectosActivos) {
        this.nombre = nombre;
        this.campoInvestigacion = campoInvestigacion;
        this.proyectosActivos = proyectosActivos;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getCampoInvestigacion() {
        return campoInvestigacion;
    }

    public int getProyectosActivos() {
        return proyectosActivos;
    }

    // Métodos abstractos para ser implementados por clases hijas
    public void disenarEstudio() {}
    public void analizarDatos() {}
    public void publicarInforme() {}
}
