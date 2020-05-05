package com.sistema.application.services;

import java.util.List;

import com.sistema.application.entities.Cliente;
import com.sistema.application.models.ClienteModel;

public interface IClienteService {

	public List<ClienteModel> getAll();
	
	//public ClienteModel findById(long id);
	
	public ClienteModel findByNroCliente(int nroCliente);
	
	public ClienteModel insertOrUpdate(ClienteModel clienteModel);
	
	public boolean remove(int id);
	
	public List<ClienteModel> findByNombreAndApellido(String nombre, String apellido);
}
