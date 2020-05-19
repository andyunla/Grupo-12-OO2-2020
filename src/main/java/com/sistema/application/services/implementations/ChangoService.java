package com.sistema.application.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sistema.application.services.IChangoService;
import com.sistema.application.repositories.IChangoRepository;
import com.sistema.application.converters.ChangoConverter;
import com.sistema.application.entities.Chango;
import com.sistema.application.models.ChangoModel;

import java.util.ArrayList;
import java.util.List;

@Service("changoService")
public class ChangoService implements IChangoService{

		//Atributos
		@Autowired
		@Qualifier("changoRepository")
		private IChangoRepository changoRepository;
		
		@Autowired
		@Qualifier("changoConverter")
		private ChangoConverter changoConverter;
		
		
		//Métodos
		@Override
		public ChangoModel findByIdChango(long idChango) {
			return changoConverter.entityToModel(changoRepository.findByIdChango(idChango) );
		}
		
		@Override
		public List<Chango> getAll(){
			return changoRepository.findAll();
		}
		
		@Override
		public List<ChangoModel> getAllModel(){
			List <ChangoModel> changos = new ArrayList<ChangoModel>();
			for(Chango c: changoRepository.findAll() ){
				changos.add(changoConverter.entityToModel(c) );
			}
			return changos;
		}
		
		@Override
		public ChangoModel insertOrUpdate(ChangoModel changoModel) {
			Chango chango = changoRepository.save(changoConverter.modelToEntity(changoModel) );
			return changoConverter.entityToModel(chango);
		}
		
		@Override
		public boolean remove(long id) {
			try {
				changoRepository.deleteById(id);
				return true;
			}catch(Exception e) {
				return false;
			}
		}
	
}
