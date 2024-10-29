package com.mycompany.poomaven;

class ProcedimientoGastroenterologico extends ProcedimientoMedico {
    public ProcedimientoGastroenterologico(int duracion) {
        super("Gastroenterológico", duracion);
    }

    @Override
    public void realizarProcedimiento() {
        System.out.println("Realizando procedimiento gastroenterológico, incluyendo endoscopia. Duración: " + duracion + " minutos.");
    }
}
