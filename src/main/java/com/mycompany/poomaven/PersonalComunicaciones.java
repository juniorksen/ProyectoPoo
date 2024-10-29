package com.mycompany.poomaven;

abstract class PersonalComunicaciones {
    protected String nombre;
    protected String canalDeComunicacion;
    protected String tipoDeMensaje;
    protected int proyectosActivos;

    public PersonalComunicaciones(String nombre, String canalDeComunicacion, String tipoDeMensaje, int proyectosActivos) {
        this.nombre = nombre;
        this.canalDeComunicacion = canalDeComunicacion;
        this.tipoDeMensaje = tipoDeMensaje;
        this.proyectosActivos = proyectosActivos; 
    }

    // Métodos getters
    public String getNombre() {
        return nombre;
    }

    public String getCanalDeComunicacion() {
        return canalDeComunicacion;
    }

    public String getTipoDeMensaje() {
        return tipoDeMensaje;
    }

    public int getProyectosActivos() {
        return proyectosActivos;
    }

    public abstract void redactarComunicado();
    public abstract void gestionarRedesSociales();
}
