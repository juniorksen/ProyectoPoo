package com.mycompany.poomaven;

// Subclase ExamenRadiologico
class ExamenRadiologico extends Examen {
    public ExamenRadiologico(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public void realizarExamen() {
        System.out.println("Realizando examen radiológico: " + nombre);
    }
}
