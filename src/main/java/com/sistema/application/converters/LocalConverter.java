package com.sistema.application.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.entities.Empleado;
import com.sistema.application.entities.Local;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.LocalModel;

@Component("localConverter")
public class LocalConverter {
	@Autowired
	@Qualifier("empleadoConverter")
	private EmpleadoConverter empleadoConverter;
	
	// Entities to models
	// *******************************************************
	public LocalModel entityToModel(Local local) {
		EmpleadoModel gerenteModel = obtenerGerenteModel(local.getGerente());
		return new LocalModel(local.getIdLocal(), local.getNombreLocal(), local.getLatitud(), local.getLongitud(),
							  local.getDireccion(), local.getTelefono(), gerenteModel);
	}
	// Método utilizado internamente para generar un modelo de local pero sin gerente
	protected LocalModel entityToModelWithoutGerente(Local local) {
		return new LocalModel(local.getIdLocal(), local.getNombreLocal(), local.getLatitud(), local.getLongitud(),
							  local.getDireccion(), local.getTelefono());
	}
	
	// Models to entities
	// *******************************************************
	public Local modelToEntity(LocalModel localModel) {
		Empleado gerente = obtenerGerente(localModel.getGerente());
		return new Local(localModel.getIdLocal(), localModel.getNombreLocal(), localModel.getLatitud(), localModel.getLongitud(), 
						 localModel.getDireccion(), localModel.getTelefono(), gerente);
	}
	// Método utilizado internamente para generar una entidad de local pero sin gerente
	protected Local modelToEntityWithoutGerente(LocalModel localModel) {
		return new Local(localModel.getIdLocal(), localModel.getNombreLocal(), localModel.getLatitud(), localModel.getLongitud(), 
						 localModel.getDireccion(), localModel.getTelefono());
	}

	/**
   	* Métodos que crea un empleado (de la entidad y modelo) pero sin establecer
   	* como argumento el local para no generar recursividad
   	*/
	private EmpleadoModel obtenerGerenteModel(Empleado gerente) {
		// Gerente sin local
		EmpleadoModel gerenteModel = empleadoConverter.entityToModelWithoutLocal(gerente);
		// Local sin gerente
		LocalModel localModel = this.entityToModelWithoutGerente(gerente.getLocal());
		
		// Les asignamos a cada uno el otro objeto
		localModel.setGerente(gerenteModel);
		gerenteModel.setLocal(localModel);
		return gerenteModel;
	}
	private Empleado obtenerGerente(EmpleadoModel gerenteModel) {
		// Gerente sin local
		Empleado gerente = empleadoConverter.modelToEntityWithoutLocal(gerenteModel);
		// Local sin gerente
		Local local = this.modelToEntityWithoutGerente(gerenteModel.getLocal());

		// Les asignamos a cada uno el otro objeto
		local.setGerente(gerente);
		gerente.setLocal(local);
		return gerente;
	}
}
