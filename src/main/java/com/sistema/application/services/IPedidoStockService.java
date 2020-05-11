package com.sistema.application.services;

import com.sistema.application.entities.PedidoStock;
import com.sistema.application.models.PedidoStockModel;

import java.util.List;

public interface IPedidoStockService {

	//public PedidoStockModel findById(long id);
	
	public List<PedidoStock> getAll();
	
	public PedidoStockModel ingresarOActualizar(PedidoStockModel pedidoStockModel);
	
	public boolean eliminar(long id);
}
