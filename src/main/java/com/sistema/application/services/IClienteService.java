package com.sistema.application.services;

import java.util.List;

import com.sistema.application.models.ClienteModel;

public interface IClienteService {

	public List<ClienteModel> getAll();
	
	//public ClienteModel findById(long id);
	
	public ClienteModel findByNroCliente(long nroCliente);
	
	public ClienteModel insertOrUpdate(ClienteModel clienteModel);
	
	public boolean remove(long id);
	
	public List<ClienteModel> findByNombreAndApellido(String nombre, String apellido);
}
