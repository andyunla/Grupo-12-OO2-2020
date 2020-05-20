package com.sistema.application.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.models.ChangoModel;
import com.sistema.application.entities.Chango;

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

	// Entites to models
	// *******************************************************
	public ChangoModel entityToModel(Chango chango) {
		return new ChangoModel( chango.getIdChango(), 
				pedidoStockConverter.entityToModel(chango.getPedidostock()),
				localConverter.entityToModel( chango.getLocal() ), 
				itemConverter.entitiesToModels( chango.getListaItems() ) 
				);
	}

	// Método utilizado internamente para generar una entidad de chango pero sin items
	public Chango modelToEntityWithoutItems(ChangoModel changoModel){
		return new Chango(changoModel.getIdChango(), 
			pedidoStockConverter.modelToEntity(changoModel.getPedidoStock()),
			localConverter.modelToEntity(changoModel.getLocal()));
	}

	// Models to entities
	// *******************************************************
	public Chango modelToEntity(ChangoModel changoModel) {
		return new Chango( changoModel.getIdChango(), 
			pedidoStockConverter.modelToEntity(changoModel.getPedidoStock()),
			localConverter.modelToEntity(changoModel.getLocal()),
			itemConverter.modelsToEntity(changoModel.getListaItems())
			);
	}

	// Método utilizado internamente para generar un modelo de chango pero sin items
	public ChangoModel entityToModelWithoutItems(Chango chango){
		return new ChangoModel( chango.getIdChango(), 
			pedidoStockConverter.entityToModel(chango.getPedidostock()),
			localConverter.entityToModel(chango.getLocal())
			);
	}

}
