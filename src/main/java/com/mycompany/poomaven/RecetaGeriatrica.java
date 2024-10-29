package com.mycompany.poomaven;

class RecetaGeriatrica extends RecetaMedica {
    public RecetaGeriatrica(String medicamento) {
        super("Geriátrica", medicamento);
    }

    @Override
    public void prescribirTratamiento() {
        System.out.println("Prescribiendo " + medicamento + " con consideraciones especiales para un paciente geriátrico.");
    }
}
