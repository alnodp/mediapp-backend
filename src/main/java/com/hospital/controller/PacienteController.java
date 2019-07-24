package com.hospital.controller;

import com.hospital.exception.ModelNotFoundException;
import com.hospital.model.Paciente;
import com.hospital.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService service;

    @GetMapping
    public ResponseEntity<List<Paciente>> listar() {
        List<Paciente> lista = service.listar();
        return new ResponseEntity<List<Paciente>>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> leerPorId(@PathVariable("id") Integer id) {
        Paciente pac = service.leerPorId(id);
        if(pac == null) {
            throw new ModelNotFoundException("Id no encontrado " + id);
        }
        return new ResponseEntity<Paciente>(pac, HttpStatus.OK);
    }

    @GetMapping("/hateoas/{id}")
    public Resource<Paciente> leerPorIdHateoas(@PathVariable("id") Integer id) {
        Paciente pac = service.leerPorId(id);
        if(pac == null) {
            throw new ModelNotFoundException("Id no encontrado " + id);
        }

        Resource<Paciente> resource = new Resource<Paciente>(pac);

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).leerPorId(id));
        resource.add(linkTo.withRel("paciente-resource"));
        return resource;
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody Paciente pac) {
        Paciente paciente = service.registrar(pac);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(paciente.getIdPaciente()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Object> modificar(@Valid @RequestBody Paciente pac) {
        service.registrar(pac);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
        Paciente pac = service.leerPorId(id);
        if(pac == null) {
            throw new ModelNotFoundException("Id no encontrado " + id);
        } else {
            service.eliminar(id);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
