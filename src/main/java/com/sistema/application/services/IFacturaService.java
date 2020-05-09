package com.sistema.application.services;

import com.sistema.application.entities.Factura;
import com.sistema.application.models.FacturaModel;

import java.util.List;

public interface IFacturaService {

	public List<Factura> getAll();
	
	public FacturaModel ingresarOActualizar(FacturaModel facturaModel);
	
	public boolean eliminar(long id);	
}
