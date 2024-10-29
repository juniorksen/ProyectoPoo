package com.mycompany.poomaven;

// Subclase Quimioterapia
class Quimioterapia extends Tratamiento {
    public Quimioterapia(String nombre, int duracionDias) {
        super(nombre, duracionDias);
    }

    @Override
    public void aplicarTratamiento() {
        System.out.println("Aplicando quimioterapia: " + nombre);
    }
}

