package com.sistema.application.services;

import com.sistema.application.entities.Chango;
import com.sistema.application.models.ChangoModel;

import java.util.List;

public interface IChangoService {

	public ChangoModel findByIdChango(long idChango);
	
	public List<Chango> getAll();
	
	public List<ChangoModel> getAllModel();
	
	public ChangoModel insertOrUpdate(ChangoModel changoModel);
	
	public boolean remove(long id);
}
