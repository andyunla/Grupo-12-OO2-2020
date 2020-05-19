package com.sistema.application.repositories;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Persona;

@Repository("personaRepository")
public interface IPersonaRepository extends JpaRepository<Persona, Serializable>{
	
	public abstract Persona findByIdPersona(long id);
	
	@Query("FROM Persona  WHERE dni=(:dni)")
	public abstract Persona findByDni(int dni);
	
	@Query("FROM Persona  WHERE nombre=(:nombre) AND apellido=(:apellido)")
	public abstract Persona findByNombreAndApellido(String nombre, String apellido);
	
	@Query("FROM Persona  WHERE fecha_nacimiento=(:fechaNacimiento)")
	public abstract Persona findByFechaNacimiento(LocalDate fechaNacimiento);
}
