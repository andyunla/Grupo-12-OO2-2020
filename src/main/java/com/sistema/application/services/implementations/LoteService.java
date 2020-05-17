package com.sistema.application.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sistema.application.services.ILoteService;
import com.sistema.application.repositories.ILoteRepository;
import com.sistema.application.converters.LoteConverter;
import com.sistema.application.converters.ProductoConverter;
import com.sistema.application.entities.Lote;
import com.sistema.application.models.LoteModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("loteService")
public class LoteService implements ILoteService {

	//Atributos
		@Autowired
		@Qualifier("loteRepository")
		private ILoteRepository loteRepository;
		
		@Autowired
		@Qualifier("loteConverter")
		private LoteConverter loteConverter;

		@Autowired
		@Qualifier("productoConverter")
		private ProductoConverter productoConverter;
		
		
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
		public Set<LoteModel> findByLoteProductoActivo(long idProducto, long idLocal){
			//creo un set list vacio
			Set<LoteModel> lista = null;
			//recorro la lista de lotes activos del poducto en el local correspondiente
			for (Lote lo : loteRepository.findByLoteProductoActivo(idProducto, idLocal)) {
				//a cada lote lo convierto de entidad a model y lo agrego a la lista
				lista.add(loteConverter.entityToModel(lo));
			}			
			return lista;
		}
		public Set<LoteModel> findByLoteProductoBaja(long idProducto, long idLocal){
			//creo un set list vacio
			Set<LoteModel> lista = null;
			//recorro la lista de lotes inactivos del poducto en el local correspondiente
			for (Lote lo : loteRepository.findByLoteProductoBaja(idProducto, idLocal)) {
				//a cada lote lo convierto de entidad a model y lo agrego a la lista
				lista.add(loteConverter.entityToModel(lo));
			}			
			return lista;
		}
 
		@Override
		public List<LoteModel> getAllModels() {
			List <LoteModel> lotes = new ArrayList<LoteModel>();
			for(Lote l: loteRepository.findAll() ){
				lotes.add( new LoteModel(
					l.getIdLote(),
					l.getCantidadInicial(),
					l.getCantidadActual(),
					l.getFechaIngreso(), 
					productoConverter.entityToModel(l.getProducto()),
					l.isActivo()
				));
			}
			return lotes;
		}
		@Override
		public List<LoteModel> getAllModels2() {
			List <LoteModel> lotes = new ArrayList<LoteModel>();
			for(Lote l: loteRepository.findAll() ){
				lotes.add(loteConverter.entityToModel(l));
			}
			return lotes;
		}
		
	
}
