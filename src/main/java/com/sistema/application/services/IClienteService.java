package com.sistema.application.services;

import java.util.List;

import com.sistema.application.models.ClienteModel;
import com.sistema.application.entities.Cliente;

public interface IClienteService {

	//public ClienteModel findById(long id);
	
	public List<Cliente> getAll();
	
	public List<ClienteModel> getAllModel();
	
	public ClienteModel insertOrUpdate(ClienteModel clienteModel);
	
	public boolean remove(long id);
	
	public ClienteModel findByNroCliente(long nroCliente);
	
	public List<ClienteModel> findByNombreAndApellido(String nombre, String apellido);
}
