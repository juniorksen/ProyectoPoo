package com.mycompany.poomaven;

// Subclase TerapiaFisica
class TerapiaFisica extends Tratamiento {
    public TerapiaFisica(String nombre, int duracionDias) {
        super(nombre, duracionDias);
    }

    @Override
    public void aplicarTratamiento() {
        System.out.println("Aplicando terapia física: " + nombre);
    }
}
