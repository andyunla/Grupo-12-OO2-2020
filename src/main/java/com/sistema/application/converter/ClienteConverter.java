package com.sistema.application.converters;

import org.springframework.stereotype.Component;

import com.sistema.application.entities.Cliente;
import com.sistema.application.models.ClienteModel;

@Component("clienteConverter")
public class ClienteConverter {

	public ClienteModel entityToModel(Cliente cliente) {
		return new ClienteModel(cliente.getIdPersona(), cliente.getNombre(), cliente.getApellido(), cliente.getDni(), cliente.getFechanacimiento(),
								cliente.getEmail(), cliente.getNroCliente());
	}
	public ClienteModel(long id, String nombre, String apellido, int dni, LocalDate fechaNacimiento, String email, int nroCliente) 
	public Cliente modelToEntity(ClienteModel clienteModel) {
		return new Cliente(clienteModel.getId(), clienteModel.getNombre(), clienteModel.getApellido(), clienteModel.getDni(), clienteModel.getFechanacimiento(),
						   clienteModel.getEmail(), clienteModel.getNroCliente());
	}
}
