package com.mycompany.poomaven;

// Subclase ExamenOrina
class ExamenOrina extends Examen {
    public ExamenOrina(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public void realizarExamen() {
        System.out.println("Realizando examen de orina: " + nombre);
    }
}
