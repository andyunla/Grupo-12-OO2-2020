package com.sistema.application.converters;

import com.sistema.application.models.PersonaModel;
import com.sistema.application.entities.Persona;

public class PersonaConverter {

	//De entidad a modelo
	public PersonaModel entityToModel(Persona persona) {
		return new PersonaModel();
	}
	
	//De modelo a entidad
	public Persona modelToEntity(PersonaModel personaModel) {
		return new Persona();
	}
	.
}
