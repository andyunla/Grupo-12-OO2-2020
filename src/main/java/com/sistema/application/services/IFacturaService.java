package com.sistema.application.services;

import com.sistema.application.entities.Factura;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.FacturaModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IFacturaService {

	public FacturaModel findByIdFactura(long idFactura);
	
	public List<Factura> getAll();
	
	public List<FacturaModel> getAllModel();
	
	public FacturaModel insertOrUpdate(FacturaModel facturaModel);
	
	public boolean remove(long id);	
	
	public  List<FacturaModel> findByFechaFacturaBetweenAndIdLocal(LocalDate fecha1, LocalDate fecha2, long idLocal);
	//public  Set<FacturaModel> findByFechaFacturaBetween(LocalDate fecha1, LocalDate fecha2);
	public  List<Factura> findByFechaFacturaBetween(LocalDate fecha1, LocalDate fecha2);

	public FacturaModel findByChango(ChangoModel chango);
}
