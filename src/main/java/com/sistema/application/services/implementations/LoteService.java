package com.sistema.application.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sistema.application.services.ILoteService;
import com.sistema.application.repositories.ILoteRepository;
import com.sistema.application.converters.LoteConverter;
import com.sistema.application.entities.Lote;
import com.sistema.application.models.LoteModel;

import java.util.List;

@Service("loteServicec")
public class LoteService implements ILoteService {

	//Atributos
		@Autowired
		@Qualifier("loteRepository")
		private ILoteRepository loteRepository;
		
		@Autowired
		@Qualifier("loteConverter")
		private LoteConverter loteConverter;
		
		
		//Métodos
		@Override
		public List<Lote> getAll(){
			return loteRepository.findAll();
		}
		
		@Override
		public LoteModel insertOrUpdate(LoteModel loteModel) {
			Lote lote = loteRepository.save(loteConverter.modelToEntity(loteModel));
			return loteConverter.entityToModel(lote);
		}
		
		@Override
		public boolean remove(long id) {
			try {
				loteRepository.deleteById(id);
				return true;
			}catch(Exception e) {
				return false;
			}
		}
	
}
