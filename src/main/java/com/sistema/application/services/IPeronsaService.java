package com.sistema.application.services;

import com.sistema.application.entities.Persona;
import com.sistema.application.models.PersonaModel;

import java.util.List;

public interface IPeronsaService {

	public List<Persona> getAll();
	
	public PersonaModel ingresarOActualizar(PersonaModel personaModel);
	
	public boolean eliminar(long id);
	
}
