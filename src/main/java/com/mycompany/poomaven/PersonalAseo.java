package com.mycompany.poomaven;

public class PersonalAseo extends Herenciaseo {
    private int id; // Añadir el campo id
    private String cedula;
    private String nombre;
    private String area;  
    private String correo;

    // Constructor
        public PersonalAseo(int id, String hora, String tiempotrabajado, String cedula, String nombre, String area, String correo) {
           super(hora, tiempotrabajado);
           this.id = id; // Necesitarás agregar un campo 'id' en la clase PersonalAseo
           this.cedula = cedula;
           this.nombre = nombre;
           this.area = area;
           this.correo = correo;
       }


    // Getters y Setters
    public int getId() { // Método getter para el id
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula; // Corregido para devolver cedula en lugar de nombre
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Método toString para representación textual del objeto
    @Override
    public String toString() {
        return "PersonalAseo{" +
                "id=" + id + // Incluir id en la representación textual
                ", hora='" + getHora() + '\'' + // Usar el getter heredado de Herenciaseo
                ", tiempotrabajado='" + getTiempotrabajado() + '\'' + // Usar el getter heredado
                ", cedula='" + cedula + '\'' + 
                ", nombre='" + nombre + '\'' + 
                ", area='" + area + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
