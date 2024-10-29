package com.mycompany.poomaven;

// Subclase Radioterapia
class Radioterapia extends Tratamiento {
    public Radioterapia(String nombre, int duracionDias) {
        super(nombre, duracionDias);
    }

    @Override
    public void aplicarTratamiento() {
        System.out.println("Aplicando radioterapia: " + nombre);
    }
}
