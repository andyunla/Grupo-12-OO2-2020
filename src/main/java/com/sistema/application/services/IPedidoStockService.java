package com.sistema.application.services;

import com.sistema.application.entities.PedidoStock;
import com.sistema.application.models.PedidoStockModel;

import java.util.List;

public interface IPedidoStockService {

	public List<PedidoStock> getAll();
	
	public PedidoStockModel insertOrUpdate(PedidoStockModel pedidoStockModel);
	
	public boolean remove(long id);
}
