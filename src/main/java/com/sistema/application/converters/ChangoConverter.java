package com.sistema.application.converters;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.ItemModel;
import com.sistema.application.entities.Chango;
import com.sistema.application.entities.Item;

@Component("changoConverter")
public class ChangoConverter {
	@Autowired
	@Qualifier("pedidoStockConverter")
	private PedidoStockConverter pedidoStockConverter;

	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;
	
	public ChangoModel entityToModel(Chango chango) {
		return new ChangoModel(chango.getIdChango(), pedidoStockConverter.entityToModel(chango.getPedidostock()), localConverter.entityToModel(chango.getLocal()));
	}
	
	public Chango modelToEntity(ChangoModel changoModel) {
		return new Chango(changoModel.getIdChango(), pedidoStockConverter.modelToEntity(changoModel.getPedidoStock()), localConverter.modelToEntity(changoModel.getLocal()));
	}
	
}
