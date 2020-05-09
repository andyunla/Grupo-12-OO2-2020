package com.sistema.application.services;

import java.util.List;

import com.sistema.application.entities.Empleado;
import com.sistema.application.models.EmpleadoModel;

public interface IEmpleadoService {

	public List<EmpleadoModel> getAll();
	
	//public EmpleadoModel findById(long id);
	
	public EmpleadoModel findByLegajo(int legajo);
	
	public EmpleadoModel insertOrUpdate(EmpleadoModel empleadoModel);
	
	public boolean remove(int id);
	
	public List<EmpleadoModel> findByNombreAndApellido(String nombre, String apellido);
}
