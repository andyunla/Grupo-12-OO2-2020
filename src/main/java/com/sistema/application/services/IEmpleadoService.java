package com.sistema.application.services;

import java.util.List;

import com.sistema.application.entities.Empleado;
import com.sistema.application.models.EmpleadoModel;

public interface IEmpleadoService {

	public List<EmpleadoModel> getAll();
	
	//public EmpleadoModel findById(long id);
	
	public EmpleadoModel findByLegajo(long legajo);
	
	public EmpleadoModel insertOrUpdate(EmpleadoModel empleadoModel);
	
	public boolean remove(long id);
	
	public List<EmpleadoModel> findByNombreAndApellido(String nombre, String apellido);

	public List<EmpleadoModel> findByIdLocal(long idLocal);
}
