package com.sistema.application.converters;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.entities.Empleado;
import com.sistema.application.entities.Local;
import com.sistema.application.funciones.Funciones;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.LocalModel;
import com.sistema.application.repositories.ILocalRepository;
import com.sistema.application.services.ILocalService;

@Component("empleadoConverter")
public class EmpleadoConverter {
	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;
	@Autowired
	@Qualifier("localRepository")
	private ILocalRepository localRepository;
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;

	// Entities to models
	// *******************************************************
	public EmpleadoModel entityToModel(Empleado empleado) {
		LocalModel localModel = obtenerLocalModel(empleado.getLocal());
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
		Local local = obtenerLocal(empleadoModel.getLocal());
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

	/**
	* Métodos que crea un local(de la entidad y modelo) pero sin establecer
	* como argumento el empleado para no generar recursividad
	*/
	private LocalModel obtenerLocalModel(Local local) {
		// Local sin gerente
		LocalModel localModel = localConverter.entityToModelWithoutGerente(local);
		// Gerente sin local
		EmpleadoModel gerenteModel = this.entityToModelWithoutLocal(local.getGerente());
		gerenteModel.setLocal(localModel);
		localModel.setGerente(gerenteModel);
		return localModel;
	}
	private Local obtenerLocal(LocalModel localModel) {
		// Local sin gerente
		Local local = localConverter.modelToEntityWithoutGerente(localModel);
		// Gerente sin local
		Empleado gerente = this.modelToEntityWithoutLocal(localModel.getGerente());
		
		// Les asignamos a cada uno el otro objeto
		gerente.setLocal(local);
		local.setGerente(gerente);
		return local;
	}
}
