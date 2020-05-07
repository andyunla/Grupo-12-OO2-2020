package com.sistema.application.converters;

import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.entities.Empleado;

public class EmpleadoConverter {

	//De entidad a modelo
	public EmpleadoModel entityToModel(Empleado empleado) {
		return new EmpleadoModel(empleado.getIdPersona(), empleado.getNombre(), empleado.getApellido(), empleado.getDni(), empleado.getFechaNacimiento(),
								 empleado.getLegajo(), empleado.getHoraDesde(), empleado.getHoraHasta(), empleado.getSueldoBasico(),
								 empleado.getLocal().getIdLocal(), empleado.getLocalOwner().getIdLocal());
	}

	//De modelo a entidad
	public Empleado modelToEntity(EmpleadoModel empleadoModel) {
		return new Empleado(empleadoModel.getId(), empleadoModel.getNombre(), empleadoModel.getApellido(), empleadoModel.getDni(), empleadoModel.getFechaNacimiento(),
							empleadoModel.getLegajo(), empleadoModel.getHoraDesde(), empleadoModel.getHoraHasta(), empleadoModel.getSueldoBasico());
	}
}
