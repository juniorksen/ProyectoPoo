package com.mycompany.poomaven;

class ProcedimientoRadiologico extends ProcedimientoMedico {
    public ProcedimientoRadiologico(int duracion) {
        super("Radiológico", duracion);
    }

    @Override
    public void realizarProcedimiento() {
        System.out.println("Realizando procedimiento radiológico con equipo de protección. Duración: " + duracion + " minutos.");
    }
}
