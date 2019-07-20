package com.hospital.service.impl;

import com.hospital.model.Paciente;
import com.hospital.repo.IPacienteRepo;
import com.hospital.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements IPacienteService {

    @Autowired
    private IPacienteRepo repo;


    @Override
    public Paciente registrar(Paciente pac) {
        return repo.save(pac);
    }

    @Override
    public void modificar(Paciente pac) {
        repo.save(pac);
    }

    @Override
    public List<Paciente> listar() {
        return repo.findAll();
    }

    @Override
    public Paciente leerPorId(Integer id) {
        return repo.findOne(id);
    }

    @Override
    public void eliminar(Integer id) {
        repo.delete(id);
    }
}
