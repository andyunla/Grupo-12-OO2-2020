package com.sistema.application.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Empleado;

@Repository("empleadoRepository")
public interface IEmpleadoRepository extends JpaRepository<Empleado, Serializable> {
	public abstract Empleado findByLegajo(long legajo);
	public abstract List<Empleado> findByNombreAndApellido(String nombre, String apellido);
	// Devuelve la lista de empleados que trabajan en un local determinado
	@Query("FROM Empleado WHERE id_local = (:idLocal)")
	public abstract List<Empleado> findByIdLocal(long idLocal);
	@Query("FROM Empleado WHERE id_local = (:idLocal) and tipo_gerente = true")
	public abstract Empleado findByGerenteLocal(long idLocal);
}
