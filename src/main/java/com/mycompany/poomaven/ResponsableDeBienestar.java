package com.mycompany.poomaven;

  abstract class ResponsableDeBienestar {
    protected String nombre;
    protected String edad;
    protected int proyectosActivos;

    public ResponsableDeBienestar (String nombre, String edad,int proyectosActivos) {
        this.nombre = nombre;
        this.edad = edad;
        this.proyectosActivos = proyectosActivos; 
    }

    public abstract void organizarActividadesRecreativas();
    public abstract void ofrecerAsesoramiento();
}
    
