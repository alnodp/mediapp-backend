package com.hospital.repo;

import com.hospital.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicoRepo extends JpaRepository<Medico, Integer> {


}
