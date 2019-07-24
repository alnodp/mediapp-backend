package com.hospital.service.impl;

import com.hospital.model.Especialidad;
import com.hospital.repo.IEspecialidadRepo;
import com.hospital.service.IEspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService {

    @Autowired
    private IEspecialidadRepo repo;

    @Override
    public Especialidad registrar(Especialidad esp) {
        return repo.save(esp);
    }

    @Override
    public Especialidad modificar(Especialidad esp) {
        return repo.save(esp);
    }

    @Override
    public List<Especialidad> listar() {
        return repo.findAll();
    }

    @Override
    public Especialidad leerPorId(Integer id) {
        return repo.findOne(id);
    }

    @Override
    public void eliminar(Integer id) {
        repo.delete(id);
    }
}
