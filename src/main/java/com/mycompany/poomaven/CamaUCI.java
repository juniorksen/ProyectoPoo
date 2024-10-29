package com.mycompany.poomaven;

// Subclase CamaUCI
class CamaUCI extends Cama {
    public CamaUCI(String ubicacion) {
        super(ubicacion);
    }

    @Override
    public void prepararCama() {
        System.out.println("Preparando cama en UCI con monitorización avanzada.");
    }
}
