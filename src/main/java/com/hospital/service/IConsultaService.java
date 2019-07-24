package com.hospital.service;

import com.hospital.dto.ConsultaListaExamenDTO;
import com.hospital.model.Consulta;

public interface IConsultaService extends ICrud<Consulta> {

    Consulta registrarTransaccional(ConsultaListaExamenDTO consultaListaExamenDTO);
}
