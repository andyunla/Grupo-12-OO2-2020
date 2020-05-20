package com.sistema.application.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sistema.application.services.IChangoService;
import com.sistema.application.services.IItemService;
import com.sistema.application.repositories.IChangoRepository;
import com.sistema.application.converters.ChangoConverter;
import com.sistema.application.entities.Chango;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.ItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("changoService")
public class ChangoService implements IChangoService{

		//Atributos
		@Autowired
		@Qualifier("changoRepository")
		private IChangoRepository changoRepository;
		
		@Autowired
		@Qualifier("changoConverter")
		private ChangoConverter changoConverter;

		@Autowired
		@Qualifier("itemService")
		private IItemService itemService;
		
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
		
		// A CONSULTAR
		@Override
		public ChangoModel insertOrUpdate(ChangoModel changoModel) {
			Chango chango = changoRepository.save(changoConverter.modelToEntity(changoModel) );
			ChangoModel changoGuardado = changoConverter.entityToModel(chango);
			if(!changoModel.getListaItems().isEmpty()){
				// Establezco el chango recien guardado como el chango de sus items porque si era un chango 
				// recién creado entonces los items referenciaban a un modelo de chango sin id
				Set<ItemModel> items = changoModel.getListaItems();
				for(ItemModel item: items){
					item.setChangoModel(changoGuardado);
				}
				// Guardo todos los items del chango y los seteo a la lista de items del chango
				// ya que ahora tienen la referencia (id) correcta al chango
				items = itemService.insertOrUpdateMany( items );
				changoGuardado.setListaItems( items ); 
			}
			return changoGuardado;
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
