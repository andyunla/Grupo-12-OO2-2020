package com.sistema.application.services;

import java.util.List;

import com.sistema.application.entities.Local;
import com.sistema.application.models.LocalModel;

public interface ILocalService {
	
	public LocalModel findByIdLocal(long idLocal);
    
    public List<Local> getAll();
	
    public List<LocalModel> getAllModel();
    
	public LocalModel insertOrUpdate(LocalModel localModel);
	
	public boolean remove(long id);
	
	public LocalModel findByNombreLocal(String nombreLocal);
	
	public LocalModel findByDireccion(String direccion);
}
