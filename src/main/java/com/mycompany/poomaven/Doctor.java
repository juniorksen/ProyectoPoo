package com.mycompany.poomaven;

public class Doctor extends Personadoc {
    private String especialidad;
    private String correo;

    public Doctor(String cedula, String nombre, String apellido, String especialidad, String correo) {
        super(cedula, nombre, apellido); // Llamar al constructor de la clase padre
        this.especialidad = especialidad;
        this.correo = correo;
    }

    // Getters
    public String getEspecialidad() {
        return especialidad;
    }

    public String getCorreo() {
        return correo;
    }

    // Setters
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "cedula='" + getCedula() + '\'' + // Usar el getter heredado
                ", nombre='" + getNombre() + '\'' + // Usar el getter heredado
                ", apellido='" + getApellido() + '\'' + // Usar el getter heredado
                ", especialidad='" + especialidad + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}

