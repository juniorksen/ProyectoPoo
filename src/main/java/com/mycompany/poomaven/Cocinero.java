package com.mycompany.poomaven;

    public class Cocinero extends Herenciacocinero {
    private String horainicio;
    private String horafin;
    private String cedula;  
    private String nombre;
    private String apellido;
    private String correo;

    // Constructor
    public Cocinero(String turnossemana, String tiempotrabajado, String horainicio, String horafin, String cedula, String nombre, String apellido, String correo) {
        super(turnossemana, tiempotrabajado); 
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    // Getters y Setters
    public String getHorainicio() {
        return horainicio; 
    }

    public void setHorainicio(String horainicio) {
        this.horainicio = horainicio;
    }

    public String getHorafin() {
        return horafin;
    }

    public void setHorafin(String horafin) {
        this.horafin = horafin;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido; // Corregido para devolver apellido
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo; // Corregido para devolver correo
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Método toString para representación textual del objeto
    @Override
    public String toString() {
        return "Cocinero{" +
                "horainicio='" + horainicio + '\'' +
                ", horafin='" + horafin + '\'' +
                ", cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", turnossemana='" + getTurnossemana() + '\'' + // Usar el getter heredado de Herenciaguardia
                ", tiempotrabajado='" + getTiempotrabajado() + '\'' + // Usar el getter heredado
                '}';
    }
}

