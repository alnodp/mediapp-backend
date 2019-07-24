package com.hospital.model;

import javax.persistence.*;

@Entity
@Table(name = "especialidad")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdEspecialidad;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    public Integer getIdEspecialidad() {
        return IdEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        IdEspecialidad = idEspecialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
