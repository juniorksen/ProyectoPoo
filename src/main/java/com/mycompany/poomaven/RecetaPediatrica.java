package com.mycompany.poomaven;

class RecetaPediatrica extends RecetaMedica {
    public RecetaPediatrica(String medicamento) {
        super("Pediátrica", medicamento);
    }

    @Override
    public void prescribirTratamiento() {
        System.out.println("Prescribiendo " + medicamento + " para un paciente pediátrico en dosis adaptadas por peso.");
    }
}
