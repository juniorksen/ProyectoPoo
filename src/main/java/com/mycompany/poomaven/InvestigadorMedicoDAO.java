package com.mycompany.poomaven;

import java.util.List;
import java.sql.SQLException;

public interface InvestigadorMedicoDAO {
    void guardarInvestigador(Investigador investigador) throws SQLException;
    List<InvestigadorMedico> obtenerTodosInvestigadores() throws SQLException; // Cambia el tipo de retorno a List<InvestigadorMedico>
}
