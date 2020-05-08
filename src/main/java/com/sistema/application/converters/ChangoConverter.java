package com.sistema.application.converters;

import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.ItemModel;
import com.sistema.application.entities.Chango;


import java.util.Set;

public class ChangoConverter {
	
	//De entidad a modelo
	public ChangoModel entityToModel(Chango chango) {
		Set<ItemModel> list = null;
		for (ItemModel itemModel : list) {
			
		}

		return new ChangoModel( chango.getIdChango(), list,chango.getPedidostock().getIdPedidoStock());
	}
	
	//De modelo a entidad
	public Chango modelToEntity(ChangoModel changoModel) {
		return new Chango(changoModel.getIdPedidostock(), changoModel.getIdPedidostock());
	}
	
}
