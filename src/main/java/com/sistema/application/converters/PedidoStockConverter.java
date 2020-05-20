package com.sistema.application.converters;

import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.PedidoStockModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.entities.Empleado;
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
		// Verifica si el oferente es nulo, de serlo entonces no realiza su conversion a modelo
		Empleado oferente = pedidoStock.getEmpleadoOferente();
		EmpleadoModel oferenteModel = (oferente == null) ? null : empleadoConverter.entityToModel(oferente);
		return new PedidoStockModel(pedidoStock.getIdPedidoStock(),
				productoConverter.entityToModel(pedidoStock.getProducto()), pedidoStock.getCantidad(),
				pedidoStock.isAceptado(), empleadoConverter.entityToModel(pedidoStock.getEmpleadoSolicitante()),
				oferenteModel);
	}

	public PedidoStock modelToEntity(PedidoStockModel pedidoStockModel) {
		// Verifica si el oferente es nulo, de serlo entonces no realiza su conversion a modelo
		EmpleadoModel oferente = pedidoStockModel.getEmpleadoOferente();
		Empleado oferenteModel = (oferente == null) ? null : empleadoConverter.modelToEntity(oferente);

		return new PedidoStock(pedidoStockModel.getIdPedidoStock(),
				productoConverter.modelToEntity(pedidoStockModel.getProducto()), pedidoStockModel.getCantidad(),
				pedidoStockModel.isAceptado(),
				empleadoConverter.modelToEntity(pedidoStockModel.getEmpleadoSolicitante()),
				oferenteModel);
	}
}
