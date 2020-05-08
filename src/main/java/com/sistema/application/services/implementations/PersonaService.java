package com.sistema.application.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sistema.application.services.IPersonaService;
import com.sistema.application.repositories.IPersonaRepository;
import com.sistema.application.converters.PersonaConverter;
import com.sistema.application.entities.Persona;
import com.sistema.application.models.PersonaModel;

import java.util.List;

@Service("personaService")
public class PersonaService implements IPersonaService{

	//Atributos
		@Autowired
		@Qualifier("personaRepository")
		private IPersonaRepository personaRepository;
		
		@Autowired
		@Qualifier("personaConverter")
		private PersonaConverter personaConverter;
		
		
		//Métodos
		@Override
		public List<Persona> getAll(){
			return personaRepository.findAll();
		}
		
		@Override
		public PersonaModel ingresarOActualizar(PersonaModel personaModel) {
			Persona persona = personaRepository.save(personaConverter.modelToEntity(personaModel) );
			return personaConverter.entityToModel(persona);
		}
		
		@Override
		public boolean eliminar(long id) {
			try {
				personaRepository.deleteById(id);
				return true;
			}catch(Exception e) {
				return false;
			}
		}
	
}
