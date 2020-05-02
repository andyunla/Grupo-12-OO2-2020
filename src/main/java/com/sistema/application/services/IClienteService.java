package com.sistema.application.services;

import java.util.List;

import com.sistema.application.entities.Cliente;
import com.sistema.application.models.ClienteModel;

public interface IClienteService {

	public List<Person> getAll();
	
	public PersonModel findById(int id);
	
	public PersonModel findByName(String name);
	
	public PersonModel insertOrUpdate(PersonModel personModel);
	
	public boolean remove(int id);
	
	public List<PersonModel> findByDegreeName(String degreeName);
}
