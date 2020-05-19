package com.sistema.application.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sistema.application.services.IPersonaService;
import com.sistema.application.repositories.IPersonaRepository;
import com.sistema.application.converters.PersonaConverter;
import com.sistema.application.entities.Persona;
import com.sistema.application.models.PersonaModel;

import java.time.LocalDate;
import java.util.ArrayList;
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
	public PersonaModel findByIdPersona(long id) {
		return personaConverter.entityToModel(personaRepository.findByIdPersona(id) );
	}
		
	@Override
	public List<Persona> getAll(){
		return personaRepository.findAll();
	}
		
	public List<PersonaModel> getAllModel(){
		List <PersonaModel> personas = new ArrayList<PersonaModel>();
		for(Persona p: personaRepository.findAll() ){
			personas.add(personaConverter.entityToModel(p) );
		}
		return personas;
	}
		
	@Override
	public PersonaModel insertOrUpdate(PersonaModel personaModel) {
		Persona persona = personaRepository.save(personaConverter.modelToEntity(personaModel));
		return personaConverter.entityToModel(persona);
	}
		
	@Override
	public boolean remove(long id) {
		try {
			personaRepository.deleteById(id);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public PersonaModel findByDni(int dni) {
		return personaConverter.entityToModel(personaRepository.findByDni(dni) );
	}
		
	@Override
	public PersonaModel findByNombreAndApellido(String nombre, String apellido) {
		return personaConverter.entityToModel(personaRepository.findByNombreAndApellido(nombre, apellido) );
	}
		
	@Override
	public PersonaModel findByFechaNacimiento(LocalDate fechaNacimiento) {
		return personaConverter.entityToModel(personaRepository.findByFechaNacimiento(fechaNacimiento) );
	}
		
}
