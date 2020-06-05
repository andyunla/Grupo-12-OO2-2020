package com.sistema.application.services;

import java.time.LocalDate;
import java.util.List;

import com.sistema.application.dto.ProductoRankingDto;
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
	
	public List<ProductoRankingDto> ranking();
	
	public List<ProductoRankingDto> reporte(LocalDate fecha1, LocalDate fecha2, long idLocal);
	
}
