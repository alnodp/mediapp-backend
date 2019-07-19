package com.hospital.repo;

import com.hospital.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepo extends JpaRepository<Paciente, Integer> {


}
