package com.mycompany.poomaven;

  abstract class CoordinadorDeProyectos {
    protected String nombre;
    protected String nombreProyecto;
    protected String presupuesto;
    protected int proyectosActivos;

    public CoordinadorDeProyectos (String nombre, String nombreProyecto, String presupuesto , int proyectosActivos) {
        this.nombre = nombre;
        this.nombreProyecto = nombreProyecto;
        this.presupuesto = presupuesto;
        this.proyectosActivos = proyectosActivos; 
    }

    public abstract void asignarRecursos();
    public abstract void establecerCronograma();
    public abstract void evaluarProgreso();
}
    
