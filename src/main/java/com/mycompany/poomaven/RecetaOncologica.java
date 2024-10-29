package com.mycompany.poomaven;

class RecetaOncologica extends RecetaMedica {
    public RecetaOncologica(String medicamento) {
        super("Oncológica", medicamento);
    }

    @Override
    public void prescribirTratamiento() {
        System.out.println("Prescribiendo " + medicamento + " con protocolo de quimioterapia para pacientes oncológicos.");
    }
}
