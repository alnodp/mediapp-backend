package com.hospital.service.impl;

import com.hospital.model.Medico;
import com.hospital.repo.IMedicoRepo;
import com.hospital.service.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoServiceImpl implements IMedicoService {

    @Autowired
    private IMedicoRepo repo;

    @Override
    public Medico registrar(Medico med) {
        return repo.save(med);
    }

    @Override
    public Medico modificar(Medico med) {
        return repo.save(med);
    }

    @Override
    public List<Medico> listar() {
        return repo.findAll();
    }

    @Override
    public Medico leerPorId(Integer id) {
        return repo.findOne(id);
    }

    @Override
    public void eliminar(Integer id) {
        repo.delete(id);
    }
}
