package com.sistema.application.services;

import com.sistema.application.entities.PedidoStock;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.PedidoStockModel;

import java.util.Set;

public interface IPedidoStockService {
	
	public Set<PedidoStockModel> getAllModels();
	
	public PedidoStockModel insertOrUpdate(PedidoStockModel pedidoStockModel);
	
	public boolean remove(long id);
	
	public PedidoStockModel findByIdPedidoStock(long idPedidoStock);
	
	public Set<PedidoStockModel> findByEmpleadoSolicitante(EmpleadoModel empleadoSolicitante);
}
