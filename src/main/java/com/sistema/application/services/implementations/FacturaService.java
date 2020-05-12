package com.sistema.application.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sistema.application.services.IFacturaService;
import com.sistema.application.repositories.IFacturaRepository;
import com.sistema.application.converters.FacturaConverter;
import com.sistema.application.entities.Factura;
import com.sistema.application.models.FacturaModel;

import java.util.List;

@Service("facturaService")
public class FacturaService implements IFacturaService{

	//Atributos
	@Autowired
	@Qualifier("facturaRepository")
	private IFacturaRepository facturaRepository;

	@Autowired
	@Qualifier("facturaConverter")
	private FacturaConverter facturaConverter;


	//Métodos
	@Override
	public List<Factura> getAll(){
		return facturaRepository.findAll();
	}

	@Override
	public FacturaModel insertOrUpdate(FacturaModel facturaModel) {
		Factura factura = facturaRepository.save(facturaConverter.modelToEntity(facturaModel) );
		return facturaConverter.entityToModel(factura);
	}

	@Override
	public boolean remove(long id) {
		try {
			facturaRepository.deleteById(id);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
}
