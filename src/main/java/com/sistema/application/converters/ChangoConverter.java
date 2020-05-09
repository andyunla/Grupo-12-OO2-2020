package com.sistema.application.converters;

import java.util.Set;
import org.springframework.stereotype.Component;

import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.ItemModel;
import com.sistema.application.entities.Chango;
import com.sistema.application.entities.Item;

@Component("changoConverter")
public class ChangoConverter {
	ItemConverter itemConverter = null;
	//De entidad a modelo
	public ChangoModel entityToModel(Chango chango) {
		// tengo que crear una lista  de itemModel para agregar al changoModel
		Set<ItemModel> list = null;
		// a cada item de esta lista tengo que convertirlo de entity a model y agregarlo a la lista
		for (Item item : chango.getListaItems()) {
			ItemModel itemModel = itemConverter.entityToModel(item);
			list.add(itemModel);
		}
		return new ChangoModel( chango.getIdChango(), list,chango.getPedidostock().getIdPedidoStock());
	}
	
	//De modelo a entidad
	public Chango modelToEntity(ChangoModel changoModel) {
		//return new Chango(changoModel.getIdPedidostock(), changoModel.getIdPedidostock());
		return new Chango();
	}
	
}
