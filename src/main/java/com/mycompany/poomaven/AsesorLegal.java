package com.mycompany.poomaven;

  abstract class AsesorLegal {
    protected String nombre;
    protected String especialidadLegal;
    protected int proyectosActivos;

    public AsesorLegal (String nombre, String especialidadLegal,int proyectosActivos) {
        this.nombre = nombre;
        this.especialidadLegal = especialidadLegal;
        this.proyectosActivos = proyectosActivos; 
    }

    public abstract void analizarNormativa();
    public abstract void asesorarDirectivos();
}
    

