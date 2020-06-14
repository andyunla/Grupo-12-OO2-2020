package com.sistema.application.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sistema.application.services.IChangoService;
import com.sistema.application.services.IFacturaService;
import com.sistema.application.services.IItemService;
import com.sistema.application.services.ILocalService;
import com.sistema.application.services.ILoteService;
import com.sistema.application.repositories.IChangoRepository;
import com.sistema.application.converters.ChangoConverter;
import com.sistema.application.converters.LocalConverter;
import com.sistema.application.entities.Chango;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.ItemModel;
import com.sistema.application.models.LocalModel;

import java.util.ArrayList;
import java.util.List;

@Service("changoService")
public class ChangoService implements IChangoService{

		//Atributos
		@Autowired
		@Qualifier("changoRepository")
		private IChangoRepository changoRepository;
		
		@Autowired
		@Qualifier("itemService")
		private IItemService itemService;
		
		@Autowired
		@Qualifier("localService")
		private ILocalService localService;

		@Autowired
		@Qualifier("facturaService")
		private IFacturaService facturaService;
		
		@Autowired
		@Qualifier("loteService")
		private ILoteService loteService;

		@Autowired
		@Qualifier("localConverter")
		private LocalConverter localConverter;

		@Autowired
		@Qualifier("changoConverter")
		private ChangoConverter changoConverter;	
		
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

		// Elimina los items del chango y al chango
		@Override
		public boolean removeWithItems(long idChango) {
			ChangoModel chango = findByIdChango(idChango);
			List<ItemModel> items = itemService.findByChango(idChango);
          	for (ItemModel item : items) {
               	loteService.devolverStock(
					chango.getLocal().getIdLocal(), 
					item.getProductoModel().getIdProducto(), 
					item.getCantidad()
				);
               	itemService.remove(item.getIdItem());
			}
			return remove(idChango);
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
