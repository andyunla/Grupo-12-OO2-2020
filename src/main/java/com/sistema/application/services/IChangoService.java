package com.sistema.application.services;

import com.sistema.application.entities.Chango;
import com.sistema.application.models.ChangoModel;

import java.util.List;

public interface IChangoService {

	public List<Chango> getAll();
	
	public ChangoModel ingresarOActualizar(ChangoModel changoModel);
	
	public boolean eliminar(long id);
}
