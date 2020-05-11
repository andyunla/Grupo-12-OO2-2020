package com.sistema.application.converters;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.entities.Empleado;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.LocalModel;

@Component("empleadoConverter")
public class EmpleadoConverter {
	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;
	
	public EmpleadoModel entityToModel(Empleado empleado) {
		return new EmpleadoModel(empleado.getIdPersona(), empleado.getNombre(), empleado.getApellido(), empleado.getDni(), empleado.getFechaNacimiento(),
								 empleado.getLegajo(), empleado.getHoraDesde(), empleado.getHoraHasta(), empleado.getSueldoBasico(),
								 localConverter.entityToModel(empleado.getLocal()), empleado.isTipoEmpleado());
	}
	
	public Empleado modelToEntity(EmpleadoModel empleadoModel) {
		return new Empleado(empleadoModel.getId(), empleadoModel.getNombre(), empleadoModel.getApellido(), empleadoModel.getDni(), empleadoModel.getFechaNacimiento(),
							empleadoModel.getLegajo(), empleadoModel.getHoraDesde(), empleadoModel.getHoraHasta(), empleadoModel.getSueldoBasico(),
							localConverter.modelToEntity(empleadoModel.getLocal()), empleadoModel.isTipoEmpleado());
	}
}
