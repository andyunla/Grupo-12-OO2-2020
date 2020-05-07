package com.sistema.application.converters;

import com.sistema.application.models.PedidoStockModel;
import com.sistema.application.entities.PedidoStock;

public class PedidoStockConverter {

	//De entidad a modelo
	public PedidoStockModel entityToModel(PedidoStock pedidoStock) {
		return new PedidoStockModel();
	}
	
	//De modelo a entidad
	public PedidoStock modelToEntity(PedidoStockModel pedidoStockModel) {
		return new PedidoStock();
	}
	.
}
