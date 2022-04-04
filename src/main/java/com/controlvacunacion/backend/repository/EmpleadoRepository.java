package com.controlvacunacion.backend.repository;

import com.controlvacunacion.backend.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {}