package com.sistema.application.services;

import java.util.List;

import com.sistema.application.models.LocalModel;

public interface ILocalService {
     public List<LocalModel> getAll();
		
	public LocalModel findByIdLocal(long iLocal);
	
	public LocalModel insertOrUpdate(LocalModel localModel);
	
	public boolean remove(long id);
	
}
