package com.mycompany.poomaven;

class ProcedimientoOrtopedico extends ProcedimientoQuirurgico {
    public ProcedimientoOrtopedico(String nombre, String descripcion) {
        super(nombre, descripcion);
    }

    @Override
    public void realizar() {
        System.out.println("Realizando procedimiento ortopédico: " + nombre);
        System.out.println("Descripción: " + descripcion);
    }
}

