package com.hospital.controller;

import com.hospital.dto.ConsultaDTO;
import com.hospital.dto.ConsultaListaExamenDTO;
import com.hospital.exception.ModelNotFoundException;
import com.hospital.model.Consulta;
import com.hospital.service.IConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private IConsultaService service;

    @GetMapping
    public ResponseEntity<List<Consulta>> listar() {
        List<Consulta> lista = service.listar();
        return new ResponseEntity<List<Consulta>>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> leerPorId(@PathVariable("id") Integer id) {
        Consulta cons = service.leerPorId(id);
        if(cons == null) {
            throw new ModelNotFoundException("Id no encontrado " + id);
        }
        return new ResponseEntity<Consulta>(cons, HttpStatus.OK);
    }

    @GetMapping("/hateoas/{id}")
    public Resource<Consulta> leerPorIdHateoas(@PathVariable("id") Integer id) {
        Consulta cons = service.leerPorId(id);
        if(cons == null) {
            throw new ModelNotFoundException("Id no encontrado " + id);
        }

        Resource<Consulta> resource = new Resource<Consulta>(cons);

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).leerPorId(id));
        resource.add(linkTo.withRel("consulta-resource"));
        return resource;
    }

    @GetMapping(value = "/hateoas", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ConsultaDTO> listarHateoas() {
        List<Consulta> consultas = new ArrayList<>();
        List<ConsultaDTO> consultasDTO = new ArrayList<>();
        consultas = service.listar();

        for (Consulta c : consultas) {
            ConsultaDTO d = new ConsultaDTO();
            d.setIdConsulta(c.getIdConsulta());
            d.setMedico(c.getMedico());
            d.setPaciente(c.getPaciente());

            // localhost:8080/consultas/1
            ControllerLinkBuilder linkTo = linkTo(methodOn(ConsultaController.class).leerPorId((c.getIdConsulta())));
            d.add(linkTo.withSelfRel());
            consultasDTO.add(d);

            // pacientes/2
            ControllerLinkBuilder linkTo1 = linkTo(
                    methodOn(PacienteController.class).leerPorId((c.getPaciente().getIdPaciente())));
            d.add(linkTo1.withSelfRel());
            consultasDTO.add(d);

            ControllerLinkBuilder linkTo2 = linkTo(
                    methodOn(MedicoController.class).leerPorId((c.getMedico().getIdMedico())));
            d.add(linkTo2.withSelfRel());

            consultasDTO.add(d);
        }

        return consultasDTO;
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody ConsultaListaExamenDTO consultaListaExamenDTO) {
        Consulta consulta = service.registrarTransaccional(consultaListaExamenDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(consulta.getIdConsulta()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Object> modificar(@Valid @RequestBody Consulta cons) {
        service.registrar(cons);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
        Consulta cons = service.leerPorId(id);
        if(cons == null) {
            throw new ModelNotFoundException("Id no encontrado " + id);
        } else {
            service.eliminar(id);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
