package com.mycompany.poomaven;

  abstract class AuditorInterno {
    protected String nombre;
    protected String areaDeAuditoria;
    protected int proyectosActivos;

    public AuditorInterno (String nombre, String areaDeAuditoria,int proyectosActivos) {
        this.nombre = nombre;
        this.areaDeAuditoria = areaDeAuditoria;
        this.proyectosActivos = proyectosActivos; 
    }

    public abstract void realizarAuditoria();
    public abstract void emitirReporte();
}
    

