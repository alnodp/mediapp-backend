package com.hospital.repo;

import com.hospital.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultaRepo extends JpaRepository<Consulta, Integer> {
}
