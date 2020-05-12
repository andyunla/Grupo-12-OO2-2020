package com.sistema.application.services;

import com.sistema.application.entities.Lote;
import com.sistema.application.models.LoteModel;

import java.util.List;

public interface ILoteService {
	
	public List<Lote> getAll();
	
	public LoteModel insertOrUpdate(LoteModel loteModel);
	
	public boolean remove(long id);
}
