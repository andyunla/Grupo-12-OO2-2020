package com.sistema.application.services;

import com.sistema.application.entities.Factura;
import com.sistema.application.models.FacturaModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IFacturaService {

	public List<Factura> getAll();
	
	public FacturaModel insertOrUpdate(FacturaModel facturaModel);
	
	public boolean remove(long id);	
	
	public  Set<FacturaModel> findFacturasEntreFechasLocal(LocalDate fecha1, LocalDate fecha2, long idLocal);
}
