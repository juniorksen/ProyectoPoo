package com.mycompany.poomaven;

// Subclase CamaPediatrica
class CamaPediatrica extends Cama {
    public CamaPediatrica(String ubicacion) {
        super(ubicacion);
    }

    @Override
    public void prepararCama() {
        System.out.println("Preparando cama pediátrica con equipamiento para niños.");
    }
}
