package com.sistema.application.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Persona;

@Repository("personaRepository")
public interface IPersonaRepository extends JpaRepository<Persona, Serializable>{
	public abstract Persona findById(long id);
	public abstract Persona findByDni(int dni);
	public abstract Persona findByNombreAndApellido(String nombre, String apellido);
	public abstract Persona findByFechaNacimiento(LocalDate fechaNacimiento);
}
