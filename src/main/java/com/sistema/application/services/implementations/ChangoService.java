package com.sistema.application.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sistema.application.services.IChangoService;
import com.sistema.application.services.IItemService;
import com.sistema.application.services.ILocalService;
import com.sistema.application.repositories.IChangoRepository;
import com.sistema.application.converters.ChangoConverter;
import com.sistema.application.converters.LocalConverter;
import com.sistema.application.entities.Chango;
import com.sistema.application.entities.Local;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.ItemModel;
import com.sistema.application.models.LocalModel;

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
		@Qualifier("localConverter")
		private LocalConverter localConverter;

		@Autowired
		@Qualifier("itemService")
		private IItemService itemService;
		
		@Autowired
		@Qualifier("localService")
		private ILocalService localService;
		
		@Autowired
		@Qualifier("localConverter")
		private LocalConverter localCoverter;
		
		
		//Métodos
		@Override
		public ChangoModel findByIdChango(long idChango) {
			try{ 
				return changoConverter.entityToModel(changoRepository.findByIdChango(idChango) );
			} catch (Exception e){
				return null;
			}
		}
		
		@Override
		public List<ChangoModel> findByLocal(LocalModel local){
			List<ChangoModel> changos = new ArrayList<ChangoModel>();
			for(Chango chango: changoRepository.findByLocalOrderByIdChangoDesc(localConverter.modelToEntity(local))){
				changos.add( changoConverter.entityToModel(chango));  
			}
			return changos;
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

		@Override 
		public double calcularTotal(long idChango) {
			List<ItemModel> itemsDelChango = itemService.findByChango(idChango);
			double total = 0;
			for(ItemModel item: itemsDelChango){
				total += item.getCantidad() * item.getProductoModel().getPrecio();
			}
			return total;
		}
	
}
