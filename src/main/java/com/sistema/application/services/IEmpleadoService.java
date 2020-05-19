package com.sistema.application.services;

import java.util.List;

import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.entities.Empleado;

public interface IEmpleadoService {
	
	//public EmpleadoModel findById(long id);
	
	public List<Empleado> getAll();

	public List<EmpleadoModel> getAllModel();
	
	public EmpleadoModel insertOrUpdate(EmpleadoModel empleadoModel);
	
	public boolean remove(long id);
	
	public EmpleadoModel findByLegajo(long legajo);
	
	public List<EmpleadoModel> findByNombreAndApellido(String nombre, String apellido);

	public List<EmpleadoModel> findByIdLocal(long idLocal);

	public EmpleadoModel findByGerenteLocal(long idLocal);
}
