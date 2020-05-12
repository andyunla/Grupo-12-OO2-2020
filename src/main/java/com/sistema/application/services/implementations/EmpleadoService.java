package com.sistema.application.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sistema.application.converters.EmpleadoConverter;
import com.sistema.application.entities.Empleado;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.repositories.IEmpleadoRepository;
import com.sistema.application.services.IEmpleadoService;


@Service("empleadoService")
public class EmpleadoService implements IEmpleadoService {

	@Autowired
	@Qualifier("empleadoRepository")
	private IEmpleadoRepository empleadoRepository;
	
	@Autowired
	@Qualifier("empleadoConverter")
	private EmpleadoConverter empleadoConverter;
	
	@Override
	public List<EmpleadoModel> getAll() {
		List <EmpleadoModel> empleados = new ArrayList <EmpleadoModel>();
		for(Empleado empleado: empleadoRepository.findAll()){
			EmpleadoModel em = empleadoConverter.entityToModel(empleado);
			empleados.add(em);
		}
		return empleados;
	}

	@Override
	public EmpleadoModel insertOrUpdate(EmpleadoModel empleadoModel) {
		Empleado empleado = empleadoRepository.save(empleadoConverter.modelToEntity(empleadoModel));
		return empleadoConverter.entityToModel(empleado);
	}

	@Override
	public boolean remove(long id) {
		try {
			List<Empleado> empleados = empleadoRepository.findAll();
			Empleado empleado = null;
			int i = 0;
			while(empleado == null && i < empleados.size()) {
				if(empleados.get(i).getIdPersona() == id) {
					empleado = empleados.get(i);
				}
				i++;
			}
			empleadoRepository.delete(empleado);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	@Override
	public EmpleadoModel findById(int id) {
		return empleadoConverter.entityToModel(empleadoRepository.findById(id));
	}
	*/
	@Override
	public EmpleadoModel findByLegajo(long legajo) {
		return empleadoConverter.entityToModel(empleadoRepository.findByLegajo(legajo));
	}
	
	@Override
	public EmpleadoModel findByGerenteLocal(long idLocal) {
		return empleadoConverter.entityToModel(empleadoRepository.findByGerenteLocal(idLocal));
	}

	@Override
	public List<EmpleadoModel> findByNombreAndApellido(String nombre, String apellido) {
		List<EmpleadoModel> models = new ArrayList<EmpleadoModel>();
		for (Empleado empleado : empleadoRepository.findByNombreAndApellido(nombre, apellido)) {
			models.add(empleadoConverter.entityToModel(empleado));
		}
		return models;
	}

	@Override
	public List<EmpleadoModel> findByIdLocal(long idLocal) {
		List<EmpleadoModel> models = new ArrayList<EmpleadoModel>();
		for (Empleado empleado : empleadoRepository.findByIdLocal(idLocal)) {
			models.add(empleadoConverter.entityToModel(empleado));
		}
		return models;
	}
}
