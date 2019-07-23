package com.hospital.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;

@ApiModel(description = "Información del Paciente")
@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaciente;

    @ApiModelProperty(notes = "Los nombres deben tener mínimo 3 caracteres")
    @Size(min = 3, message = "Los nombres deben tener mínimo 3 caracteres")
    @Column(name = "nombres", nullable = false, length = 70)
    private String nombres;

    @ApiModelProperty(notes = "Los apellidos deben tener mínimo 3 caracteres")
    @Size(min = 3, message = "Los apellidos deben tener mínimo 3 caracteres")
    @Column(name = "apellidos", nullable = false, length = 70)
    private String apellidos;

    @ApiModelProperty(notes = "El dni debe tener 8 caracteres")
    @Size(min = 8, max = 8, message = "El dni debe tener 8 caracteres")
    @Column(name = "dni", nullable = false, length = 8)
    private String dni;

    @ApiModelProperty(notes = "La dirección debe tener mínimo 3 caracteres")
    @Size(min = 3, max = 150, message = "La dirección debe tener mínimo 3 caracteres")
    @Column(name = "direccion", nullable = true, length = 150)
    private String direccion;

    @ApiModelProperty(notes = "El teléfono debe tener 9 caracteres")
    @Size(min = 9, max = 9, message = "El teléfono debe tener 9 caracteres")
    @Column(name = "telefono", nullable = false, length = 9)
    private String telefono;

    @Email
    @Column(name = "email", nullable = false, length = 55)
    private String email;

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
