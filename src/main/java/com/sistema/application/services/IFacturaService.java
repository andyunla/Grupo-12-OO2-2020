package com.sistema.application.services;

import com.sistema.application.entities.Factura;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.FacturaModel;

import java.time.LocalDate;
import java.util.List;

public interface IFacturaService {

	public FacturaModel findByIdFactura(long idFactura);
	
	public List<Factura> getAll();
	
	public List<FacturaModel> getAllModel();
	
	public FacturaModel insertOrUpdate(FacturaModel facturaModel);
	
	public boolean remove(long id);	
	
	public  List<FacturaModel> findByFechaFacturaBetweenAndIdLocal(LocalDate fecha1, LocalDate fecha2, long idLocal);
	
	public  List<Factura> findByFechaFacturaBetween(LocalDate fecha1, LocalDate fecha2);

	public FacturaModel findByChango(ChangoModel chango);

	public List<FacturaModel> findByIdLocal(long idLocal);

	public List<FacturaModel> findByIdLocalAndByLegajoEmpleado(long idLocal, long legajo);
	
	public FacturaModel facturaPedido(long idPedidoStock, long nroCliente);
}
