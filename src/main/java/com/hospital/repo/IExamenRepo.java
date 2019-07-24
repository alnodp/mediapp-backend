package com.hospital.repo;

import com.hospital.model.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExamenRepo extends JpaRepository<Examen, Integer> {


}
