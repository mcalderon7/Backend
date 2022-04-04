package com.controlvacunacion.backend.repository;

import com.controlvacunacion.backend.model.Vacuna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacunaRepository extends JpaRepository<Vacuna, Long> {}