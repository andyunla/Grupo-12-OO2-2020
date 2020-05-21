package com.sistema.application.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.PedidoStockModel;
import com.sistema.application.entities.Chango;
import com.sistema.application.entities.PedidoStock;

@Component("changoConverter")
public class ChangoConverter {
	@Autowired
	@Qualifier("pedidoStockConverter")
	private PedidoStockConverter pedidoStockConverter;

	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;

	@Autowired
	@Qualifier("itemConverter")
	private ItemConverter itemConverter;

	public ChangoModel entityToModel(Chango chango) {
		// Verifica si el pedidoStock es nulo, de serlo no lo convierte a modelo
		PedidoStock pedidoEntidad = chango.getPedidostock();
		PedidoStockModel pedidoModel = (pedidoEntidad == null) ? null : pedidoStockConverter.entityToModel(pedidoEntidad);
		return new ChangoModel( chango.getIdChango(), pedidoModel, localConverter.entityToModel( chango.getLocal()) );
	}

	public Chango modelToEntity(ChangoModel changoModel) {
		// Verifica si el pedidoStock es nulo, de serlo no lo convierte a modelo
		PedidoStockModel pedidoModel = changoModel.getPedidoStock();
		PedidoStock pedidoEntidad = (pedidoModel == null) ? null : pedidoStockConverter.modelToEntity(pedidoModel);
		return new Chango( changoModel.getIdChango(), pedidoEntidad, localConverter.modelToEntity(changoModel.getLocal()));
	}

}
