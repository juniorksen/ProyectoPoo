package com.mycompany.poomaven;

public class Paciente extends Persona {
    private int edad;
    private String direccion;
    private String correo;
    private String enfermedad;
    private String pronostico;
    

    public Paciente(String cedula, String nombre, String apellido, int edad, String direccion, String correo, String enfermedad, String pronostico) {
        super(cedula, nombre, apellido);
        this.edad = edad;
        this.direccion = direccion;
        this.correo = correo;
        this.enfermedad = enfermedad;
        this.pronostico = pronostico;
    }

    // Sobrescribiendo el setter de 'nombre' para validar
    @Override
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        super.setNombre(nombre); // Llama al setter de la clase padre
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getPronostico() {
        return pronostico;
    }

    public void setPronostico(String pronostico) {
        this.pronostico = pronostico;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "cedula='" + getCedula() + '\'' + // Usar el getter heredado
                ", nombre='" + getNombre() + '\'' + // Usar el getter heredado
                ", apellido='" + getApellido() + '\'' + // Usar el getter heredado
                ", edad=" + edad +
                ", direccion='" + direccion + '\'' +
                ", correo='" + correo + '\'' +
                ", enfermedad='" + enfermedad + '\'' +
                ", pronostico='" + pronostico + '\'' +
                '}';
    }
}
