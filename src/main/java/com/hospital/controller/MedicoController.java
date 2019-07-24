package com.hospital.controller;

import com.hospital.exception.ModelNotFoundException;
import com.hospital.model.Medico;
import com.hospital.service.IMedicoService;
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
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private IMedicoService service;

    @GetMapping
    public ResponseEntity<List<Medico>> listar() {
        List<Medico> lista = service.listar();
        return new ResponseEntity<List<Medico>>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> leerPorId(@PathVariable("id") Integer id) {
        Medico med = service.leerPorId(id);
        if(med == null) {
            throw new ModelNotFoundException("Id no encontrado " + id);
        }
        return new ResponseEntity<Medico>(med, HttpStatus.OK);
    }

    @GetMapping("/hateoas/{id}")
    public Resource<Medico> leerPorIdHateoas(@PathVariable("id") Integer id) {
        Medico med = service.leerPorId(id);
        if(med == null) {
            throw new ModelNotFoundException("Id no encontrado " + id);
        }

        Resource<Medico> resource = new Resource<Medico>(med);

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).leerPorId(id));
        resource.add(linkTo.withRel("medico-resource"));
        return resource;
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody Medico med) {
        Medico medico = service.registrar(med);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medico.getIdMedico()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Object> modificar(@Valid @RequestBody Medico med) {
        service.registrar(med);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
        Medico med = service.leerPorId(id);
        if(med == null) {
            throw new ModelNotFoundException("Id no encontrado " + id);
        } else {
            service.eliminar(id);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
