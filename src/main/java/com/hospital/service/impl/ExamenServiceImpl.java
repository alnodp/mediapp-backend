package com.hospital.service.impl;

import com.hospital.model.Examen;
import com.hospital.repo.IExamenRepo;
import com.hospital.service.IExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamenServiceImpl implements IExamenService {

    @Autowired
    private IExamenRepo repo;

    @Override
    public Examen registrar(Examen exa) {
        return repo.save(exa);
    }

    @Override
    public Examen modificar(Examen exa) {
        return repo.save(exa);
    }

    @Override
    public List<Examen> listar() {
        return repo.findAll();
    }

    @Override
    public Examen leerPorId(Integer id) {
        return repo.findOne(id);
    }

    @Override
    public void eliminar(Integer id) {
        repo.delete(id);
    }
}
