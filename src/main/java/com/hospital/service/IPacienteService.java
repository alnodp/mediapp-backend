package com.hospital.service;

import com.hospital.model.Paciente;

import java.util.List;

public interface IPacienteService {

    void registrar(Paciente pac);
    void modificar(Paciente pac);
    List<Paciente> listar();
    Paciente leerPorId(Integer id);
    void eliminar(Integer id);

}
