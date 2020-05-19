package com.sistema.application.converters;

import com.sistema.application.models.PedidoStockModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.entities.PedidoStock;

@Component("pedidoStockConverter")
public class PedidoStockConverter {
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	@Autowired
	@Qualifier("empleadoConverter")
	private EmpleadoConverter empleadoConverter;
	
	public PedidoStockModel entityToModel(PedidoStock pedidoStock) {
		return new PedidoStockModel(pedidoStock.getIdPedidoStock(), productoConverter.entityToModel(pedidoStock.getProducto()), 
				pedidoStock.getCantidad(), pedidoStock.isAceptado(), empleadoConverter.entityToModel(pedidoStock.getEmpleadoSolicitante()), empleadoConverter.entityToModel(pedidoStock.getEmpleadoOferente()));
	}
	
	public PedidoStock modelToEntity(PedidoStockModel pedidoStockModel) {		
		return new PedidoStock(pedidoStockModel.getIdPedidoStock(), productoConverter.modelToEntity(pedidoStockModel.getProducto()), 
				pedidoStockModel.getCantidad(), pedidoStockModel.isAceptado(), empleadoConverter.modelToEntity(pedidoStockModel.getEmpleadoSolicitante()), empleadoConverter.modelToEntity(pedidoStockModel.getEmpleadoOferente()));
	}
}
