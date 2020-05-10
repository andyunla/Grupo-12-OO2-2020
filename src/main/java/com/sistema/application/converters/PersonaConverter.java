package com.sistema.application.converters;

import com.sistema.application.models.PersonaModel;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.sistema.application.entities.Persona;

@Component("personaConverter")
public class PersonaConverter {

	//De entidad a modelo
	public PersonaModel entityToModel(Persona persona) {
		return new PersonaModel(persona.getIdPersona(), persona.getNombre(),persona.getApellido(), persona.getDni(), persona.getFechaNacimiento());
	}
	
	//De modelo a entidad
	public Persona modelToEntity(PersonaModel personaModel) {
		return new Persona(personaModel.getId(), personaModel.getNombre(), personaModel.getApellido(),personaModel.getDni(), personaModel.getFechaNacimiento());
	}
	
}
