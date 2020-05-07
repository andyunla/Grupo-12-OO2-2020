package com.sistema.application.converters;

import com.sistema.application.models.LocalModel;
import com.sistema.application.entities.Local;

public class LocalConverter {

	//De entidad a modelo
	public LocalModel entityToModel(Local local) {
		return new LocalModel();
	}
	
	//De modelo a entidad
	public Local modelToEntity(LocalModel localModel) {
		return new Local();
	}
	.
}
