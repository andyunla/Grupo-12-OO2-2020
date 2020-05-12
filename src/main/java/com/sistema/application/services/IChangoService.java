package com.sistema.application.services;

import com.sistema.application.entities.Chango;
import com.sistema.application.models.ChangoModel;

import java.util.List;

public interface IChangoService {

	public List<Chango> getAll();
	
	public ChangoModel insertOrUpdate(ChangoModel changoModel);
	
	public boolean remove(long id);
}
