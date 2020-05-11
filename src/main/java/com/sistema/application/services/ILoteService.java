package com.sistema.application.services;

import com.sistema.application.entities.Lote;
import com.sistema.application.models.LoteModel;

import java.util.List;

public interface ILoteService {
	
	//public LoteModel findById(long id);
	
	public List<Lote> getAll();
	
	public LoteModel ingresarOActualizar(LoteModel loteModel);
	
	public boolean eliminar(long id);
}
