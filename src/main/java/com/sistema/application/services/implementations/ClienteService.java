package com.sistema.application.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sistema.application.converters.ClienteConverter;
import com.sistema.application.entities.Cliente;
import com.sistema.application.models.ClienteModel;
import com.sistema.application.repositories.IClienteRepository;
import com.sistema.application.services.IClienteService;


@Service("clienteService")
public class ClienteService implements IClienteService {

	@Autowired
	@Qualifier("clienteRepository")
	private IClienteRepository clienteRepository;
	
	@Autowired
	@Qualifier("clienteConverter")
	private ClienteConverter clienteConverter;
	
	@Override
	public List<ClienteModel> getAll() {
		List <ClienteModel> clientes = new ArrayList <ClienteModel>();
		for(Cliente c: clienteRepository.findAll()){
			ClienteModel cm = clienteConverter.entityToModel(c);
			clientes.add(cm);
		}
		return clientes;
	}

	@Override
	public ClienteModel insertOrUpdate(ClienteModel clienteModel) {
		Cliente cliente = clienteRepository.save(clienteConverter.modelToEntity(clienteModel));
		return clienteConverter.entityToModel(cliente);
	}

	@Override
	public boolean remove(int id) {
		try {
			clienteRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	/*
	@Override
	public ClienteModel findById(int id) {
		return clienteConverter.entityToModel(clienteRepository.findById(id));
	}
	*/
	@Override
	public ClienteModel findByNroCliente(int nroCliente) {
		return clienteConverter.entityToModel(clienteRepository.findByNroCliente(nroCliente));
	}
	
	@Override
	public List<ClienteModel> findByNombreAndApellido(String nombre, String apellido) {
		List<ClienteModel> models = new ArrayList<ClienteModel>();
		for (Cliente cliente : clienteRepository.findByNombreAndApellido(nombre, apellido)) {
			models.add(clienteConverter.entityToModel(cliente));
		}
		return models;
	}
}
