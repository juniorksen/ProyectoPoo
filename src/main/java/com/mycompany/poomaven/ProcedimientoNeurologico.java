    package com.mycompany.poomaven;

class ProcedimientoNeurologico extends ProcedimientoQuirurgico {
    public ProcedimientoNeurologico(String nombre, String descripcion) {
        super(nombre, descripcion);
    }

    @Override
    public void realizar() {
        System.out.println("Realizando procedimiento neurológico: " + nombre);
        System.out.println("Descripción: " + descripcion);
    }
}
