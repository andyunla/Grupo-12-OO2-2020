package com.sistema.application.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.dto.LocalDto;
import com.sistema.application.entities.Empleado;
import com.sistema.application.entities.Local;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.LocalModel;
import com.sistema.application.repositories.IEmpleadoRepository;

@Component("localConverter")
public class LocalConverter {
	@Autowired
	@Qualifier("empleadoConverter")
	private EmpleadoConverter empleadoConverter;
	@Autowired
	@Qualifier("empleadoRepository")
	private IEmpleadoRepository empleadoRepository;
	
	// Entities to models
	// *******************************************************
	public LocalModel entityToModel(Local local) {
		EmpleadoModel gerenteModel = null;
		if(local.getGerente() != null) {
			gerenteModel = empleadoConverter.entityToModelWithoutLocal(local.getGerente()); // Gerente sin local
		} else {
			gerenteModel = new EmpleadoModel();
		}
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
		Empleado gerente;
		if(localModel.getGerente() != null) {
			//gerente = empleadoConverter.modelToEntityWithoutLocal(localModel.getGerente()); // Gerente sin local
			gerente = empleadoRepository.findByLegajo(localModel.getGerente().getLegajo());
		} else {
			gerente = null;
		}
		return new Local(localModel.getIdLocal(), localModel.getNombreLocal(), localModel.getLatitud(), localModel.getLongitud(), 
						 localModel.getDireccion(), localModel.getTelefono(), gerente);
	}
	// Método utilizado internamente para generar una entidad de local pero sin gerente
	protected Local modelToEntityWithoutGerente(LocalModel localModel) {
		return new Local(localModel.getIdLocal(), localModel.getNombreLocal(), localModel.getLatitud(), localModel.getLongitud(), 
						 localModel.getDireccion(), localModel.getTelefono());
	}

	// Entities to DTO
	// *******************************************************
	public LocalDto entityToDto(Local local) {
		return new LocalDto(local.getIdLocal(), local.getNombreLocal(), local.getLatitud(), local.getLongitud(),
							local.getDireccion(), local.getTelefono(), local.getGerente().getLegajo());
	}
	public Local dtoToEntity(LocalDto localDto) {
		Empleado gerente = empleadoRepository.findByLegajo(localDto.getGerenteLegajo());
		return new Local(localDto.getIdLocal(), localDto.getNombreLocal(), localDto.getLatitud(), localDto.getLongitud(),
						 localDto.getDireccion(), localDto.getTelefono(), gerente);
	}

	// models to DTO
	// *******************************************************
	public LocalDto modelToDto(LocalModel localModel) {
		return new LocalDto(localModel.getIdLocal(), localModel.getNombreLocal(), localModel.getLatitud(), localModel.getLongitud(), 
							localModel.getDireccion(), localModel.getTelefono(), localModel.getGerente().getLegajo());
	}
	public LocalModel dtoToModel(LocalDto localDto) {
		Empleado empleado = empleadoRepository.findByLegajo(localDto.getGerenteLegajo());
		EmpleadoModel gerenteModel = empleadoConverter.entityToModel(empleado);
		return new LocalModel(localDto.getIdLocal(), localDto.getNombreLocal(), localDto.getLatitud(), localDto.getLongitud(),
							  localDto.getDireccion(), localDto.getTelefono(), gerenteModel);
	}
}
