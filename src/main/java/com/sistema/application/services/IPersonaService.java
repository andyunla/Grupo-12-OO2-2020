package com.sistema.application.services;

import com.sistema.application.entities.Persona;
import com.sistema.application.models.PersonaModel;

import java.time.LocalDate;
import java.util.List;

public interface IPersonaService {
	
	public PersonaModel findByIdPersona(long id);

	public List<Persona> getAll();
	
	public List<PersonaModel> getAllModel();
	
	public PersonaModel insertOrUpdate(PersonaModel personaModel);
	
	public boolean remove(long id);
	
	public PersonaModel findByDni(int dni);
	
	public PersonaModel findByNombreAndApellido(String nombre, String apellido);
	
	public PersonaModel findByFechaNacimiento(LocalDate fechaNacimiento);
}
