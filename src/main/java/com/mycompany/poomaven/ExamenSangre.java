package com.mycompany.poomaven;

// Subclase ExamenSangre
class ExamenSangre extends Examen {
    public ExamenSangre(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public void realizarExamen() {
        System.out.println("Realizando examen de sangre: " + nombre);
    }
}
