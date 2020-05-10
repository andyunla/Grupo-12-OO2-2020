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
		return new PedidoStockModel(pedidoStock.getIdPedidoStock(),productoConverter.entityToModel(pedidoStock.getProducto()), 
				pedidoStock.getCantidad(), pedidoStock.isAceptado(), pedidoStock.getSolicitanteLegajo(),
				pedidoStock.getOferenteLegajo());
	}
	
	//De modelo a entidad
	public PedidoStock modelToEntity(PedidoStockModel pedidoStockModel) {
		//revisar el oferente, solicitante. También el isAceptado()
		IProductoRepository iPR = null;
		Producto prod = iPR.findByIdProducto(pedidoStockModel.getIdProducto());
		IEmpleadoRepository iER = null;
		Empleado solicitante = iER.findByLegajo((int)pedidoStockModel.getIdSolicitante());
		Empleado oferente = iER.findByLegajo((int)pedidoStockModel.getIdOferente());
		return new PedidoStock(prod, pedidoStockModel.getCantidad(), solicitante, pedidoStockModel.isAceptado(),  oferente);
	}
	
}
