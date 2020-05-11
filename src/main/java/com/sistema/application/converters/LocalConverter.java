package com.sistema.application.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.entities.Local;
import com.sistema.application.models.LocalModel;

@Component("localConverter")
public class LocalConverter {	
	@Autowired
	@Qualifier("empleadoConverter")
	private EmpleadoConverter empleadoConverter;

	public LocalModel entityToModel(Local local) {
		return new LocalModel(local.getIdLocal(), local.getNombreLocal(), local.getLatitud(), local.getLongitud(),
							  local.getDireccion(), local.getTelefono(), local.getGerenteLegajo());
	}
	
	public Local modelToEntity(LocalModel localModel) {
		return new Local(localModel.getIdLocal(), localModel.getNombreLocal(), localModel.getLatitud(), localModel.getLongitud(), 
						 localModel.getDireccion(), localModel.getTelefono(), localModel.getGerenteLegajo());
	}
}
