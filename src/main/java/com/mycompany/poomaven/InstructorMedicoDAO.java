package com.mycompany.poomaven;

import java.util.List;

public interface InstructorMedicoDAO {
    List<InstructorMedico> obtenerTodosInstructores();
    void agregarInstructor(InstructorMedico instructor);
    void eliminarInstructor(int id);
    void actualizarInstructor(InstructorMedico instructor);
}
