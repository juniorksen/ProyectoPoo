package com.mycompany.poomaven;

  abstract class EspecialistaTI {
    protected String nombre;
    protected String nivelAcceso;
    protected String herramientasUtilizadas;
    protected int proyectosActivos;

    public EspecialistaTI (String nombre, String nivelAcceso, String herramientasUtilizadas , int proyectosActivos) {
        this.nombre = nombre;
        this.nivelAcceso = nivelAcceso;
        this.herramientasUtilizadas = herramientasUtilizadas;
        this.proyectosActivos = proyectosActivos; 
    }

    public abstract void mantenerSistema();
    public abstract void resolverIncidente();
}
    
