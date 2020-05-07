package com.sistema.application.converters;

import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.entities.Empleado;

public class EmpleadoConverter {

	//De entidad a modelo
	public EmpleadoModel entityToModel(Empleado empleado) {
		return new EmpleadoModel();
	}
	
	//De modelo a entidad
	public Empleado modelToEntity(EmpleadoModel empleadoModel) {
		return new Empleado();
	}
	.
}
