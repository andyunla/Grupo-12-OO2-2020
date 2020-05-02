package com.sistema.application.converters;

import org.springframework.stereotype.Component;

import com.sistema.application.entities.Cliente;
import com.sistema.application.models.ClienteModel;

@Component("clienteConverter")
public class ClienteConverter {

	public ClienteModel entityToModel(Cliente cliente) {
		return new ClienteModel(cliente.getIdPersona(), cliente.getNombre(), cliente.getApellido(), cliente.getDni(), cliente.getFechaNacimiento(),
								cliente.getEmail(), cliente.getNroCliente());
	}
	
	public Cliente modelToEntity(ClienteModel clienteModel) {
		return new Cliente(clienteModel.getId(), clienteModel.getNombre(), clienteModel.getApellido(), clienteModel.getDni(), clienteModel.getFechaNacimiento(),
						   clienteModel.getEmail(), clienteModel.getNroCliente());
	}
}
