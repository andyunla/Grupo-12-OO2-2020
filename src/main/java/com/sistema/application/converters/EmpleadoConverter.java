package com.sistema.application.converters;

import com.sistema.application.models.EmpleadoModel;

import java.time.LocalDate;
import java.time.LocalTime;

import com.sistema.application.entities.Empleado;
import com.sistema.application.entities.Local;

public class EmpleadoConverter {

	//De entidad a modelo
	public EmpleadoModel entityToModel(Empleado empleado) {
		// casteo (int)empleado.getLegajo(), (int)empleado.getLocal().getIdLocal()
		return new EmpleadoModel(empleado.getNombre(), empleado.getApellido(), empleado.getDni(), empleado.getFechaNacimiento(), 
				(int)empleado.getLegajo(), empleado.getHoraDesde(),empleado.getHoraHasta(), empleado.getSueldoBasico(), (int)empleado.getLocal().getIdLocal());
	}
	
	//De modelo a entidad
	public Empleado modelToEntity(EmpleadoModel empleadoModel) {
		return new Empleado(long idPersona, empleadoModel.getNombre(), empleadoModel.getApellido(), empleadoModel.getDni(), empleadoModel.getFechaNacimiento(), empleadoModel.getLegajo(), 
				empleadoModel.getHoraDesde(), empleadoModel.getHoraHasta(), empleadoModel.getSueldoBasico(), Local local, Local localOwner);
	}
	
}
