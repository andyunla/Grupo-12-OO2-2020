package com.sistema.application.repositories;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Empleado;
import com.sistema.application.entities.Persona;
public interface IEmpleadoRepository extends JpaRepository<Persona, Serializable>{
	public abstract Empleado findByLegajo(int legajo);
}
