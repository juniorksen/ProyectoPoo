package com.mycompany.poomaven;

class ProcedimientoCardiaco extends ProcedimientoQuirurgico {
    public ProcedimientoCardiaco(String nombre, String descripcion) {
        super(nombre, descripcion);
    }

    @Override
    public void realizar() {
        System.out.println("Realizando procedimiento cardiaco: " + nombre);
        System.out.println("Descripción: " + descripcion);
    }
}
