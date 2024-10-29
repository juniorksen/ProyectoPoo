package com.mycompany.poomaven;

class ProcedimientoCardiologico extends ProcedimientoMedico {
    public ProcedimientoCardiologico(int duracion) {
        super("Cardiológico", duracion);
    }

    @Override
    public void realizarProcedimiento() {
        System.out.println("Realizando procedimiento cardiológico con monitoreo cardíaco continuo. Duración: " + duracion + " minutos.");
    }
}
