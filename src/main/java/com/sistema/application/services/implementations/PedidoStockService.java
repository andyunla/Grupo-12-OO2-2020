package com.sistema.application.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sistema.application.services.IPedidoStockService;
import com.sistema.application.repositories.IPedidoStockRepository;
import com.sistema.application.converters.PedidoStockConverter;
import com.sistema.application.entities.PedidoStock;
import com.sistema.application.models.PedidoStockModel;

import java.util.List;

@Service("pedidoStockService")
public class PedidoStockService implements IPedidoStockService{

	//Atributos
		@Autowired
		@Qualifier("pedidoStockRepository")
		private IPedidoStockRepository pedidoStockRepository;
		
		@Autowired
		@Qualifier("pedidoStockConverter")
		private PedidoStockConverter pedidoStockConverter;
		
		
		//Métodos
		@Override
		public List<PedidoStock> getAll(){
			return pedidoStockRepository.findAll();
		}
		
		@Override
		public PedidoStockModel insertOrUpdate(PedidoStockModel pedidoStockModel) {
			PedidoStock pedidoStock = pedidoStockRepository.save(pedidoStockConverter.modelToEntity(pedidoStockModel) );
			return pedidoStockConverter.entityToModel(pedidoStock);
		}
		
		@Override
		public boolean remove(long id) {
			try {
				pedidoStockRepository.deleteById(id);
				return true;
			}catch(Exception e) {
				return false;
			}
		}
		public PedidoStockModel findByIdPedidoStock(long idPedidoStock) {
			return pedidoStockConverter.entityToModel(pedidoStockRepository.findByIdPedidoStock(idPedidoStock));
		}
	
}
