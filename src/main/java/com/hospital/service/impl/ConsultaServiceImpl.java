package com.hospital.service.impl;

import com.hospital.dto.ConsultaListaExamenDTO;
import com.hospital.model.Consulta;
import com.hospital.repo.IConsultaExamenRepo;
import com.hospital.repo.IConsultaRepo;
import com.hospital.service.IConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ConsultaServiceImpl implements IConsultaService {

    @Autowired
    private IConsultaRepo repo;

    @Autowired
    private IConsultaExamenRepo ceRepo;

    @Override
    public Consulta registrar(Consulta cons) {
        cons.getDetalleConsulta().forEach(det -> {
            det.setConsulta(cons);
        });
        return repo.save(cons);
    }

    @Transactional
    @Override
    public Consulta registrarTransaccional(ConsultaListaExamenDTO consultaListaExamenDTO) {
        consultaListaExamenDTO.getConsulta().getDetalleConsulta().forEach(det -> det.setConsulta(consultaListaExamenDTO.getConsulta()));
        repo.save(consultaListaExamenDTO.getConsulta());

        consultaListaExamenDTO.getListExamen()
                .forEach(ex -> ceRepo.registrar(consultaListaExamenDTO.getConsulta().getIdConsulta(), ex.getIdExamen()));
        return consultaListaExamenDTO.getConsulta();
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
