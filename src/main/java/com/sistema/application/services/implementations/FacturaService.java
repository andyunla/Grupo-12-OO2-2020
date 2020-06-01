package com.sistema.application.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sistema.application.services.IFacturaService;
import com.sistema.application.repositories.IFacturaRepository;
import com.sistema.application.converters.ChangoConverter;
import com.sistema.application.converters.FacturaConverter;
import com.sistema.application.entities.Factura;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.FacturaModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("facturaService")
public class FacturaService implements IFacturaService {

	//Atributos
	@Autowired
	@Qualifier("facturaRepository")
	private IFacturaRepository facturaRepository;

	@Autowired
	@Qualifier("facturaConverter")
	private FacturaConverter facturaConverter;

	@Autowired
	@Qualifier("changoConverter")
	private ChangoConverter changoConverter;


	//Métodos
	@Override
	public FacturaModel findByIdFactura(long idFactura) {
		return facturaConverter.entityToModel(facturaRepository.findByIdFactura(idFactura) );
	}
	
	
	@Override
	public List<Factura> getAll(){
		return facturaRepository.findAll();
	}

	@Override
	public List<FacturaModel> getAllModel(){
		List <FacturaModel> facturas = new ArrayList<FacturaModel>();
		for(Factura f: facturaRepository.findAll() ){
			facturas.add(facturaConverter.entityToModel(f) );
		}
		return facturas;
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
	
	@Override
	public  Set<FacturaModel> findByFechaFacturaBetweenAndIdLocal(LocalDate fecha1, LocalDate fecha2, long idLocal){
		Set<FacturaModel> lista = null;// crei una lista de facturas
		for (Factura fa : facturaRepository.findByFechaFacturaBetweenAndIdLocal(fecha1, fecha2, idLocal)) {//traigo la lista de facturas entre fechas d eun local
			lista.add(facturaConverter.entityToModel(fa));// las agrego a la lista model
		}
		return lista;
	}
	
	@Override
	public  Set<FacturaModel> findByFechaFacturaBetween(LocalDate fecha1, LocalDate fecha2){
		Set<FacturaModel> lista = null;// crei una lista de facturas
		for (Factura fa : facturaRepository.findByFechaFacturaBetween(fecha1, fecha2 )) {//traigo la lista de facturas entre fechas d eun local
			lista.add(facturaConverter.entityToModel(fa));// las agrego a la lista model
		}
		return lista;
	}

	@Override
	public FacturaModel findByChango(ChangoModel chango) {
		try{
			Factura factura = facturaRepository.findByChango(changoConverter.modelToEntity(chango));
			return facturaConverter.entityToModel(factura);  
		}catch(Exception e) {
			return null;
		}
	}
}
