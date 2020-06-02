package com.sistema.application.services;

import com.sistema.application.entities.Chango;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.LocalModel;

import java.util.List;

public interface IChangoService {

	public ChangoModel findByIdChango(long idChango);

	public List<ChangoModel> findByLocal(LocalModel local);
	
	public List<Chango> getAll();
	
	public List<ChangoModel> getAllModel();
	
	public ChangoModel insertOrUpdate(ChangoModel changoModel);
	
	public boolean remove(long id);
}
