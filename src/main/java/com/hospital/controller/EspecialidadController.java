package com.hospital.controller;

import com.hospital.exception.ModelNotFoundException;
import com.hospital.model.Especialidad;
import com.hospital.model.Medico;
import com.hospital.service.IEspecialidadService;
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
@RequestMapping("/especialidades")
public class EspecialidadController {

    @Autowired
    private IEspecialidadService service;

    @GetMapping
    public ResponseEntity<List<Especialidad>> listar() {
        List<Especialidad> lista = service.listar();
        return new ResponseEntity<List<Especialidad>>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> leerPorId(@PathVariable("id") Integer id) {
        Especialidad esp = service.leerPorId(id);
        if(esp == null) {
            throw new ModelNotFoundException("Id no encontrado " + id);
        }
        return new ResponseEntity<Especialidad>(esp, HttpStatus.OK);
    }

    @GetMapping("/hateoas/{id}")
    public Resource<Especialidad> leerPorIdHateoas(@PathVariable("id") Integer id) {
        Especialidad esp = service.leerPorId(id);
        if(esp == null) {
            throw new ModelNotFoundException("Id no encontrado " + id);
        }

        Resource<Especialidad> resource = new Resource<Especialidad>(esp);

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).leerPorId(id));
        resource.add(linkTo.withRel("especialidad-resource"));
        return resource;
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody Especialidad esp) {
        Especialidad especialidad = service.registrar(esp);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(especialidad.getIdEspecialidad()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Object> modificar(@Valid @RequestBody Especialidad esp) {
        service.registrar(esp);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
        Especialidad esp = service.leerPorId(id);
        if(esp == null) {
            throw new ModelNotFoundException("Id no encontrado " + id);
        } else {
            service.eliminar(id);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
