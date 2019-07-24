package com.hospital.service.impl;

import com.hospital.model.Consulta;
import com.hospital.repo.IConsultaRepo;
import com.hospital.service.IConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaServiceImpl implements IConsultaService {

    @Autowired
    private IConsultaRepo repo;

    @Override
    public Consulta registrar(Consulta cons) {
        cons.getDetalleConsulta().forEach(det -> {
            det.setConsulta(cons);
        });
        return repo.save(cons);
    }

    @Override
    public Consulta modificar(Consulta cons) {
        return repo.save(cons);
    }

    @Override
    public List<Consulta> listar() {
        return repo.findAll();
    }

    @Override
    public Consulta leerPorId(Integer id) {
        return repo.findOne(id);
    }

    @Override
    public void eliminar(Integer id) {
        repo.delete(id);
    }
}
