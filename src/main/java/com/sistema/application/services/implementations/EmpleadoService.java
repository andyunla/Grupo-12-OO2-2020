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

	//Atributos
	@Autowired
	@Qualifier("empleadoRepository")
	private IEmpleadoRepository empleadoRepository;
	
	@Autowired
	@Qualifier("empleadoConverter")
	private EmpleadoConverter empleadoConverter;
	
	
	//Métodos
	public List<Empleado> getAll(){
		return empleadoRepository.findAll();
	}
	
	@Override
	public List<EmpleadoModel> getAllModel() {
		List <EmpleadoModel> empleados = new ArrayList <EmpleadoModel>();
		for(Empleado empleado: empleadoRepository.findAll()){
			if(empleado.getIdPersona() != 19 ) {//filtro para que no traiga al ADMIN
			EmpleadoModel em = empleadoConverter.entityToModel(empleado);
			empleados.add(em);
			}
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

	
	@Override
	public EmpleadoModel findByLegajo(long legajo) {
		return empleadoConverter.entityToModel(empleadoRepository.findByLegajo(legajo));
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
			if(empleado.getIdPersona() !=19) { //filtro para que no traiga al ADMIN
			models.add(empleadoConverter.entityToModel(empleado));
			}
		}
		return models;
	}
	
	@Override
	public EmpleadoModel findByGerenteLocal(long idLocal) {
		return empleadoConverter.entityToModel(empleadoRepository.findByGerenteLocal(idLocal));
	}
	
}
