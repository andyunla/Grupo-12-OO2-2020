package com.sistema.application.converters;

import com.sistema.application.models.PedidoStockModel;
import com.sistema.application.repositories.IEmpleadoRepository;
import com.sistema.application.repositories.IProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.entities.Empleado;
import com.sistema.application.entities.PedidoStock;
import com.sistema.application.entities.Producto;

@Component("pedidoStockConverter")
public class PedidoStockConverter {
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	//De entidad a modelo
	public PedidoStockModel entityToModel(PedidoStock pedidoStock) {
		//revisar el oferente, solicitante. También el isAceptado() LEANDRO
		return new PedidoStockModel(pedidoStock.getIdPedidoStock(), productoConverter.entityToModel(pedidoStock.getProducto()), 
				pedidoStock.getCantidad(), pedidoStock.isAceptado(), pedidoStock.getSolicitanteLegajo(),
				pedidoStock.getOferenteLegajo());
	}
	
	//De modelo a entidad
	public PedidoStock modelToEntity(PedidoStockModel pedidoStockModel) {		
		return new PedidoStock(pedidoStockModel.getIdPedidoStock(), productoConverter.modelToEntity(pedidoStockModel.getProducto()), 
				pedidoStockModel.getCantidad(), pedidoStockModel.isAceptado(), pedidoStockModel.getIdSolicitante(),  pedidoStockModel.getIdOferente());
	}
	
}
