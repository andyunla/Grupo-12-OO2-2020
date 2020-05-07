package com.sistema.application.converters;

import com.sistema.application.models.ChangoModel;
import com.sistema.application.entities.Chango;

public class ChangoConverter {

	//De entidad a modelo
	public ChangoModel entityToModel(Chango chango) {
		return new ChangoModel(chango.getIdChango(), chango.getListaItems(), chango.getPedidostock().getIdPedidoStock() );
	}
	
	//De modelo a entidad
	public Chango modelToEntity(ChangoModel changoModel) {
		return new Chango(changoModel);
	}
	
}
