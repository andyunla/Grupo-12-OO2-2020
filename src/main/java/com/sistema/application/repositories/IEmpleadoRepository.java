package com.sistema.application.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Empleado;

@Repository("empleadoRepository")
public interface IEmpleadoRepository extends JpaRepository<Empleado, Serializable>{
	public abstract Empleado findByLegajo(long legajo);
	public abstract List<Empleado> findByNombreAndApellido(String nombre, String apellido);
}
