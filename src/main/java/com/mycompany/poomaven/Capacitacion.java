package com.mycompany.poomaven;

abstract class Capacitacion {
    protected String nombre;
    protected String campoInvestigacion;
    protected int proyectosActivos;

    public Capacitacion(String nombre, String campoInvestigacion, int proyectosActivos) {
        this.nombre = nombre;
        this.campoInvestigacion = campoInvestigacion;
        this.proyectosActivos = proyectosActivos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCampoInvestigacion() {
        return campoInvestigacion;
    }

    public int getProyectosActivos() {
        return proyectosActivos;
    }

    public abstract void organizarTaller();
    public abstract void monitorearProgreso();
    public abstract void InformeProgreso();
}
