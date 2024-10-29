package com.mycompany.poomaven;

// Subclase CamaQuirurgica
class CamaQuirurgica extends Cama {
    public CamaQuirurgica(String ubicacion) {
        super(ubicacion);
    }

    @Override
    public void prepararCama() {
        System.out.println("Preparando cama quirúrgica con soporte de oxígeno y equipo estéril.");
    }
}
