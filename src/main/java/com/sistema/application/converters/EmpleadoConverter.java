package com.sistema.application.converters;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.entities.Empleado;
import com.sistema.application.entities.Local;
import com.sistema.application.funciones.Funciones;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.LocalModel;
import com.sistema.application.models.dto.EmpleadoDto;
import com.sistema.application.repositories.ILocalRepository;

@Component("empleadoConverter")
public class EmpleadoConverter {
	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;
	
	@Autowired
	@Qualifier("localRepository")
	private ILocalRepository localRepository;

	// Entities to models
	// *******************************************************
	public EmpleadoModel entityToModel(Empleado empleado) {
		LocalModel localModel = localConverter.entityToModelWithoutGerente(empleado.getLocal()); // Local sin gerente
		return new EmpleadoModel(empleado.getIdPersona(), empleado.getNombre(), empleado.getApellido(), empleado.getDni(), empleado.getFechaNacimiento(),
								 empleado.getLegajo(), empleado.getHoraDesde().toString(), empleado.getHoraHasta().toString(), empleado.getSueldoBasico(),
								 localModel, empleado.isTipoGerente());
	}
	// Método utilizado internamente para generar un modelo de gerente pero sin local
	protected EmpleadoModel entityToModelWithoutLocal(Empleado empleado) {
		return new EmpleadoModel(empleado.getIdPersona(), empleado.getNombre(), empleado.getApellido(), empleado.getDni(), empleado.getFechaNacimiento(),
								 empleado.getLegajo(), empleado.getHoraDesde().toString(), empleado.getHoraHasta().toString(), empleado.getSueldoBasico(),
								 empleado.isTipoGerente());
	}

	// Models to entities
	// *******************************************************
	public Empleado modelToEntity(EmpleadoModel empleadoModel) {
		LocalTime horaDesde = Funciones.horaFromString(empleadoModel.getHoraDesde());
		LocalTime horaHasta = Funciones.horaFromString(empleadoModel.getHoraHasta());
		Local local = localConverter.modelToEntityWithoutGerente(empleadoModel.getLocal()); // Local sin gerente
		return new Empleado(empleadoModel.getId(), empleadoModel.getNombre(), empleadoModel.getApellido(), empleadoModel.getDni(), empleadoModel.getFechaNacimiento(),
							empleadoModel.getLegajo(), horaDesde, horaHasta, empleadoModel.getSueldoBasico(),
							local, empleadoModel.isTipoGerente());
	}
	// Método utilizado internamente para generar una entidad de gerente pero sin local
	protected Empleado modelToEntityWithoutLocal(EmpleadoModel empleadoModel) {
		LocalTime horaDesde = Funciones.horaFromString(empleadoModel.getHoraDesde());
		LocalTime horaHasta = Funciones.horaFromString(empleadoModel.getHoraHasta());
		return new Empleado(empleadoModel.getId(), empleadoModel.getNombre(), empleadoModel.getApellido(), empleadoModel.getDni(), empleadoModel.getFechaNacimiento(),
							empleadoModel.getLegajo(), horaDesde, horaHasta, empleadoModel.getSueldoBasico(),
							empleadoModel.isTipoGerente());
	}

	// Entities to DTO
	// *******************************************************
	public EmpleadoDto entityToDto(Empleado empleado) {
		return new EmpleadoDto(empleado.getIdPersona(), empleado.getNombre(), empleado.getApellido(), empleado.getDni(), empleado.getFechaNacimiento(),
							   empleado.getLegajo(), empleado.getHoraDesde().toString(), empleado.getHoraHasta().toString(), empleado.getSueldoBasico(),
							   empleado.getLocal().getIdLocal(), empleado.isTipoGerente());
	}
	public Empleado dtoToEntity(EmpleadoDto empleadoDto) {
		LocalTime horaDesde = Funciones.horaFromString(empleadoDto.getHoraDesde());
		LocalTime horaHasta = Funciones.horaFromString(empleadoDto.getHoraHasta());
		Local local = localRepository.findByIdLocal(empleadoDto.getIdLocal());
		return new Empleado(empleadoDto.getId(), empleadoDto.getNombre(), empleadoDto.getApellido(), empleadoDto.getDni(), empleadoDto.getFechaNacimiento(),
							empleadoDto.getLegajo(), horaDesde, horaHasta, empleadoDto.getSueldoBasico(),
							local, empleadoDto.isTipoGerente());
	}

	// models to DTO
	// *******************************************************
	public EmpleadoDto modelToDto(EmpleadoModel empleadoModel) {
		return new EmpleadoDto(empleadoModel.getId(), empleadoModel.getNombre(), empleadoModel.getApellido(), empleadoModel.getDni(), empleadoModel.getFechaNacimiento(),
							   empleadoModel.getLegajo(), empleadoModel.getHoraDesde().toString(), empleadoModel.getHoraHasta().toString(), empleadoModel.getSueldoBasico(),
							   empleadoModel.getLocal().getIdLocal(), empleadoModel.isTipoGerente());
	}
	public EmpleadoModel dtoToModel(EmpleadoDto empleadoDto) {
		Local local = localRepository.findByIdLocal(empleadoDto.getIdLocal());
		LocalModel localModel = localConverter.entityToModel(local);
		return new EmpleadoModel(empleadoDto.getId(), empleadoDto.getNombre(), empleadoDto.getApellido(), empleadoDto.getDni(), empleadoDto.getFechaNacimiento(),
								 empleadoDto.getLegajo(), empleadoDto.getHoraDesde().toString(), empleadoDto.getHoraHasta().toString(), empleadoDto.getSueldoBasico(),
								 localModel, empleadoDto.isTipoGerente());
	}
}
