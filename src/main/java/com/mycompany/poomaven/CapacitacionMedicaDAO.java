package com.mycompany.poomaven;

import java.util.List;

public interface CapacitacionMedicaDAO {
    List<CapacitacionMedico> getAllCapacitaciones();
    List<CapacitacionMedico> getCapacitacionesByProyectosActivos(int proyectosActivos);
}
