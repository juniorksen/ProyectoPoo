package com.mycompany.poomaven;

abstract class GestorMantenimiento {
    protected String nombre;
    protected String areaResponsabilidad;
    protected String tipoDeEquipo;
    protected int proyectosActivos;

    public GestorMantenimiento(String nombre, String areaResponsabilidad, String tipoDeEquipo, int proyectosActivos) {
        this.nombre = nombre;
        this.areaResponsabilidad = areaResponsabilidad;
        this.tipoDeEquipo = tipoDeEquipo;
        this.proyectosActivos = proyectosActivos;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getAreaResponsabilidad() {
        return areaResponsabilidad;
    }

    public String getTipoDeEquipo() {
        return tipoDeEquipo;
    }

    public int getProyectosActivos() {
        return proyectosActivos;
    }

    // Métodos abstractos
    public abstract void programarMantenimiento();
    public abstract void repararEquipo();
}
