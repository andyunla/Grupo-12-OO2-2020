package com.sistema.application.converters;

import com.sistema.application.models.LocalModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.entities.Local;

@Component("localConverter")
public class LocalConverter {

	// NECESITA EL CONVERTER DE EMPLEADO IMPLEMENTADO
	@Autowired
	@Qualifier("empleadoConverter")
	private EmpleadoConverter empleadoConverter;

	//De entidad a modelo
	public LocalModel entityToModel(Local local) {
		return new LocalModel( local.getIdLocal(), local.getNombreLocal(), local.getLatitud(), local.getLongitud(),
			local.getDireccion(), local.getTelefono(), empleadoConverter.entityToModel(local.getGerente()) );
	}
	
	//De modelo a entidad
	public Local modelToEntity(LocalModel localModel) {
		return new Local( localModel.getIdLocal(), localModel.getNombreLocal(), localModel.getLatitud(), 
			localModel.getLongitud(), localModel.getDireccion(), localModel.getTelefono(), 
			empleadoConverter.modelToEntity( localModel.getGerente() ));
	}
}
